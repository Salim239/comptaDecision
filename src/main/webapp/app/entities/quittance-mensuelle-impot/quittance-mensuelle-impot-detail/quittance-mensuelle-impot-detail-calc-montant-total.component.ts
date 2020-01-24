import {Component, Input, OnInit} from '@angular/core';
import {IQuittanceMensuelleImpotDetail} from "app/shared/model/quittance-mensuelle-impot-detail.model";

@Component({
    selector: 'quittance-mensuelle-impot-detail-calc-montant-total',
    templateUrl: './quittance-mensuelle-impot-detail-calc-montant-total.component.html'
})
export class QuittanceMensuelleImpotDetailCalcMontantTotalComponent implements OnInit {

    @Input() quittanceMensuelleImpotDetail: IQuittanceMensuelleImpotDetail;

    constructor() {

    }

    ngOnInit() {
    }
}
