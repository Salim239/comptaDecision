import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { IQuittanceMensuelleImpotDetail } from 'app/shared/model/quittance-mensuelle-impot-detail.model';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-detail-calc-montant-total',
    templateUrl: './quittance-mensuelle-impot-detail-calc-montant-total.component.html'
})
export class QuittanceMensuelleImpotDetailCalcMontantTotalComponent implements OnInit {
    @Input() quittanceMensuelleImpotDetail: IQuittanceMensuelleImpotDetail;
    @Output() calculerMontants = new EventEmitter();

    constructor() {}

    ngOnInit() {}

    isMontantReportChanged() {
        return (
            this.quittanceMensuelleImpotDetail.appliquerReportMontant &&
            this.quittanceMensuelleImpotDetail.montantReportCalc !== this.quittanceMensuelleImpotDetail.montantReport
        );
    }

    onChangeMontantReport() {
        this.calculerMontants.emit();
    }
}
