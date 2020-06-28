import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { IQuittanceMensuelleImpotDetail } from 'app/shared/model/quittance-mensuelle-impot-detail.model';
import { TypeValeur } from 'app/shared/model/impot-mensuel-detail.model';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-detail-list',
    templateUrl: './quittance-mensuelle-impot-detail-list.component.html'
})
export class QuittanceMensuelleImpotDetailListComponent implements OnInit {
    @Input() quittanceMensuelleImpotDetail: IQuittanceMensuelleImpotDetail;
    @Input() indexDetail: number;
    @Output() calculerMontants = new EventEmitter();

    constructor() {}

    ngOnInit() {}

    isTaux(typeValeur) {
        return typeValeur === TypeValeur.TAUX;
    }

    isMontantForfaitaire(quittanceMensuelleImpotSousDetail) {
        return (
            quittanceMensuelleImpotSousDetail.impotMensuelDetailTypeValeur === TypeValeur.MONTANT &&
            quittanceMensuelleImpotSousDetail.impotMensuelDetailValeur &&
            quittanceMensuelleImpotSousDetail.impotMensuelDetailValeur !== 1
        );
    }

    onChangeMontantBase() {
        this.calculerMontants.emit();
    }

    onChangeMontantTotal() {
        this.calculerMontants.emit();
    }
}
