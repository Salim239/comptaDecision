import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { IQuittanceMensuelleLigne } from 'app/shared/model/quittance-mensuelle-ligne.model';
import { TypeValeur } from 'app/shared/model/impot-mensuel-ligne.model';

@Component({
    selector: 'jhi-quittance-mensuelle-ligne-list',
    templateUrl: './quittance-mensuelle-ligne-list.component.html'
})
export class QuittanceMensuelleLigneListComponent implements OnInit {
    @Input() quittanceMensuelleLigne: IQuittanceMensuelleLigne;
    @Input() indexLigne: number;
    @Output() calculerMontants = new EventEmitter();

    constructor() {}

    ngOnInit() {}

    isTaux(typeValeur) {
        return typeValeur === TypeValeur.TAUX;
    }

    isMontantForfaitaire(quittanceMensuelleSousLigne) {
        return (
            quittanceMensuelleSousLigne.impotMensuelLigneTypeValeur === TypeValeur.MONTANT &&
            quittanceMensuelleSousLigne.impotMensuelLigneValeur &&
            quittanceMensuelleSousLigne.impotMensuelLigneValeur !== 1
        );
    }

    onChangeMontantBase() {
        this.calculerMontants.emit();
    }

    onChangeMontantTotal() {
        this.calculerMontants.emit();
    }
}
