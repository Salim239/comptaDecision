import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {IQuittanceMensuelleImpotDetail} from 'app/shared/model/quittance-mensuelle-impot-detail.model';
import {TypeValeur} from 'app/shared/model/impot-mensuel-detail.model';
import ComptaDecisionUtils from 'app/shared/util/compta-decision-utils';
import * as _ from 'lodash';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-detail-list',
    templateUrl: './quittance-mensuelle-impot-detail-list.component.html'
})
export class QuittanceMensuelleImpotDetailListComponent implements OnInit {

    @Input() quittanceMensuelleImpotDetail: IQuittanceMensuelleImpotDetail;
    @Input() indexDetail: number;
    @Output() updateMontantTotal = new EventEmitter();

    private formatMontantBase(quittanceMensuelleImpotSousDetail) {
        quittanceMensuelleImpotSousDetail.montantBase = ComptaDecisionUtils.parseCurrency(quittanceMensuelleImpotSousDetail.montantBase);
        quittanceMensuelleImpotSousDetail.montantBase = ComptaDecisionUtils.formatCurrency(quittanceMensuelleImpotSousDetail.montantBase);
    }

    private parseMontantBase(quittanceMensuelleImpotSousDetail) {
        quittanceMensuelleImpotSousDetail.montantBase = ComptaDecisionUtils.parseCurrency(quittanceMensuelleImpotSousDetail.montantBase);
    }

    constructor() {

    }

    ngOnInit() {
        const that = this;
        _.each(this.quittanceMensuelleImpotDetail.quittanceMensuelleImpotSousDetails, function(quittanceMensuelleImpotSousDetail) {
            that.formatMontantBase(quittanceMensuelleImpotSousDetail) ;
        });
    }

    isTaux(typeValeur) {
        return typeValeur === TypeValeur.TAUX;
    }

    isMontantForfaitaire(quittanceMensuelleImpotSousDetail) {

        return quittanceMensuelleImpotSousDetail.impotMensuelDetailTypeValeur === TypeValeur.MONTANT &&
            quittanceMensuelleImpotSousDetail.impotMensuelDetailValeur &&
            quittanceMensuelleImpotSousDetail.impotMensuelDetailValeur !== 1;
    }

    calculerImpotDetailValeur(quittanceMensuelleImpotSousDetail) {
        return (this.isTaux(quittanceMensuelleImpotSousDetail.impotMensuelDetailTypeValeur) ?
            parseFloat(quittanceMensuelleImpotSousDetail.impotMensuelDetailValeur) / 100 :
            parseFloat(quittanceMensuelleImpotSousDetail.impotMensuelDetailValeur));
    }

    calculerMontantTotal(indexSousDetail) {
        const impotDetailValeur = this.calculerImpotDetailValeur(this.quittanceMensuelleImpotDetail.quittanceMensuelleImpotSousDetails[indexSousDetail]);
        this.quittanceMensuelleImpotDetail.quittanceMensuelleImpotSousDetails[indexSousDetail].montantTotal =
            impotDetailValeur * this.quittanceMensuelleImpotDetail.quittanceMensuelleImpotSousDetails[indexSousDetail].montantBase;
        this.formatMontantBase(this.quittanceMensuelleImpotDetail.quittanceMensuelleImpotSousDetails[indexSousDetail]);
        this.updateMontantTotal.emit(this.quittanceMensuelleImpotDetail);
    }
}
