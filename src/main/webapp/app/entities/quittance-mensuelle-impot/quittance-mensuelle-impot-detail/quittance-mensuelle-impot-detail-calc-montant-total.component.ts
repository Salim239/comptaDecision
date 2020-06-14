import { Component, Input, OnInit } from '@angular/core';
import { IQuittanceMensuelleImpotDetail } from 'app/shared/model/quittance-mensuelle-impot-detail.model';
import ComptaDecisionUtils from 'app/shared/util/compta-decision-utils';
import * as _ from 'lodash';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-detail-calc-montant-total',
    templateUrl: './quittance-mensuelle-impot-detail-calc-montant-total.component.html'
})
export class QuittanceMensuelleImpotDetailCalcMontantTotalComponent implements OnInit {
    @Input() quittanceMensuelleImpotDetail: IQuittanceMensuelleImpotDetail;

    constructor() {}

    ngOnInit() {
        this.parseMontants();
        this.formatMontants();
    }

    private formatMontants() {
        this.quittanceMensuelleImpotDetail.montantReport = ComptaDecisionUtils.formatCurrency(
            this.quittanceMensuelleImpotDetail.montantReport
        );
        _.each(this.quittanceMensuelleImpotDetail.childQuittanceMensuelleImpotDetails, function(child) {
            child.montantTotal = ComptaDecisionUtils.formatCurrency(child.montantTotal);
        });
    }

    private parseMontants() {
        this.quittanceMensuelleImpotDetail.montantReport = ComptaDecisionUtils.parseCurrency(
            this.quittanceMensuelleImpotDetail.montantReport
        );
        _.each(this.quittanceMensuelleImpotDetail.childQuittanceMensuelleImpotDetails, function(child) {
            child.montantTotal = ComptaDecisionUtils.parseCurrency(child.montantTotal);
        });
    }
}
