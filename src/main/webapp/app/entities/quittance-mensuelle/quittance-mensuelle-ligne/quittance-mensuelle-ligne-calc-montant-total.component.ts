import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { IQuittanceMensuelleLigne } from 'app/shared/model/quittance-mensuelle-ligne.model';

@Component({
    selector: 'jhi-quittance-mensuelle-ligne-calc-montant-total',
    templateUrl: './quittance-mensuelle-ligne-calc-montant-total.component.html'
})
export class QuittanceMensuelleLigneCalcMontantTotalComponent implements OnInit {
    @Input() quittanceMensuelleLigne: IQuittanceMensuelleLigne;
    @Output() calculerMontants = new EventEmitter();

    constructor() {}

    ngOnInit() {}

    isMontantReportChanged() {
        return (
            this.quittanceMensuelleLigne.appliquerReportMontant &&
            this.quittanceMensuelleLigne.montantReportCalc !== this.quittanceMensuelleLigne.montantReport
        );
    }

    onChangeMontantReport() {
        this.calculerMontants.emit();
    }
}
