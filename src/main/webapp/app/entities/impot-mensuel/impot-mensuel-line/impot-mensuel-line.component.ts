import { Component, Input, OnInit } from '@angular/core';
import { IImpotMensuelLigne, ImpotMensuelLigne } from 'app/shared/model/impot-mensuel-ligne.model';
import { IImpotMensuel } from 'app/shared/model/impot-mensuel.model';

@Component({
    selector: 'jhi-impot-mensuel-line',
    templateUrl: './impot-mensuel-line.component.html',
    styles: []
})
export class ImpotMensuelLineComponent implements OnInit {
    @Input() impotMensuel: IImpotMensuel;
    impotMensuelLigneNew: IImpotMensuelLigne;

    newImpotMensuelLigne() {
        return new ImpotMensuelLigne(
            undefined,
            this.impotMensuel.impotMensuelLignes.length === 0 ? 1 : this.impotMensuel.impotMensuelLignes.length + 1
        );
    }

    isValidImpotMensuelLigne(impotMensuelLigne) {
        return (
            impotMensuelLigne.code &&
            impotMensuelLigne.code !== '' &&
            impotMensuelLigne.libelle &&
            impotMensuelLigne.libelle !== '' &&
            impotMensuelLigne.triOrdre &&
            impotMensuelLigne.triOrdre !== ''
        );
    }

    /**
     * Recalculate tri ordre of impotMensuelLigne for elements since given index in the array
     * @param recalFromIndex
     */
    recalculateImpotMensuelLigneTriOrdre(recalFromIndex) {
        this.impotMensuel.impotMensuelLignes.forEach(impotMensuelLigne => {
            if (impotMensuelLigne.triOrdre - 1 > recalFromIndex) {
                impotMensuelLigne.triOrdre = impotMensuelLigne.triOrdre - 1;
                return impotMensuelLigne;
            }
        });
    }

    addImpotMensuelLigne(impotMensuelLigneNew) {
        this.impotMensuel.impotMensuelLignes.push(impotMensuelLigneNew);
        this.impotMensuelLigneNew = this.newImpotMensuelLigne();
    }

    editImpotMensuelLigne(impotMensuelLigne, impotMensuelLigneIndex) {
        this.impotMensuelLigneNew = impotMensuelLigne;
        this.impotMensuel.impotMensuelLignes.splice(impotMensuelLigneIndex, 1);
    }

    deleteImpotMensuelLigne(impotMensuelLigneIndex) {
        this.impotMensuel.impotMensuelLignes.splice(impotMensuelLigneIndex, 1);
        this.recalculateImpotMensuelLigneTriOrdre(impotMensuelLigneIndex);
        this.impotMensuelLigneNew = this.newImpotMensuelLigne();
    }

    constructor() {}

    ngOnInit() {
        this.impotMensuelLigneNew = this.newImpotMensuelLigne();
    }
}
