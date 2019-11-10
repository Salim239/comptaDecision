import {Component, Input, OnInit} from '@angular/core';
import {IQuittanceMensuelleImpotDetail} from "app/shared/model/quittance-mensuelle-impot-detail.model";
import {TypeValeur} from "app/shared/model/impot-mensuel-detail.model";
import * as _ from 'lodash';

@Component({
    selector: 'quittance-mensuelle-impot-detail-list',
    templateUrl: './quittance-mensuelle-impot-detail-list.component.html'
})
export class QuittanceMensuelleImpotDetailListComponent implements OnInit {

    @Input() quittanceMensuelleImpotDetail: IQuittanceMensuelleImpotDetail;
    @Input() indexDetail: number;


    constructor() {

    }

    ngOnInit() {
    }

    isTaux (typeValeur) {
        return typeValeur === TypeValeur.TAUX
    }

    calculerImpotDetailValeur(quittanceMensuelleImpotSousDetail) {
        return (this.isTaux(quittanceMensuelleImpotSousDetail.impotMensuelDetailTypeValeur) ?
            quittanceMensuelleImpotSousDetail.impotMensuelDetailValeur / 100 :
            quittanceMensuelleImpotSousDetail.impotMensuelDetailValeur);
    }

    calculerMontantTotal(indexSousDetail) {
        var impotDetailValeur = this.calculerImpotDetailValeur(this.quittanceMensuelleImpotDetail.quittanceMensuelleImpotSousDetails[indexSousDetail]);
        this.quittanceMensuelleImpotDetail.quittanceMensuelleImpotSousDetails[indexSousDetail].montantTotal =
            impotDetailValeur * this.quittanceMensuelleImpotDetail.quittanceMensuelleImpotSousDetails[indexSousDetail].montantBase;
        var that = this;
        this.quittanceMensuelleImpotDetail.montantTotal = _.sum(
            _.map(this.quittanceMensuelleImpotDetail.quittanceMensuelleImpotSousDetails, function(quittanceMensuelleImpotSousDetail) {
                return that.calculerImpotDetailValeur(quittanceMensuelleImpotSousDetail) * quittanceMensuelleImpotSousDetail.montantBase;
            })
        );
    }
}
