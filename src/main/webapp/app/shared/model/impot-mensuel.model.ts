import { IImpotMensuelLigne } from 'app/shared/model/impot-mensuel-ligne.model';

export interface IImpotMensuel {
    id?: number;
    code?: string;
    libelle?: string;
    description?: string;
    child?: boolean;
    parent?: boolean;
    parentImpotMensuelId?: number;
    parentImpotMensuelLibelle?: string;
    appliquerReportMontant?: boolean;
    coefficientMontant?: number;
    impotMensuelLignes?: IImpotMensuelLigne[];
    childImpotMensuels?: IImpotMensuel[];
}

export class ImpotMensuel implements IImpotMensuel {
    constructor(
        public id?: number,
        public code?: string,
        public libelle?: string,
        public parent?: boolean,
        public child?: boolean,
        public description?: string,
        public parentImpotMensuelId?: number,
        public parentImpotMensuelLibelle?: string,
        public appliquerReportMontant?: boolean,
        public coefficientMontant?: number,
        public impotMensuelLignes?: IImpotMensuelLigne[],
        public childImpotMensuels?: IImpotMensuel[]
    ) {
        this.impotMensuelLignes = [];
        this.childImpotMensuels = [];
        this.appliquerReportMontant = false;
        this.coefficientMontant = 1;
    }
}
