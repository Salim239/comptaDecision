import { IQuittanceMensuelleSousLigne } from 'app/shared/model/quittance-mensuelle-sous-ligne.model';

export interface IQuittanceMensuelleLigne {
    id?: number;
    code?: string;
    libelle?: string;
    description?: string;
    parent?: boolean;
    child?: boolean;
    parentQuittanceMensuelleLigneId?: number;
    parentQuittanceMensuelleLigneCode?: string;
    parentQuittanceMensuelleLigneLibelle?: string;
    childQuittanceMensuelleLignes?: [IQuittanceMensuelleLigne];
    quittanceMensuelleId?: number;
    impotMensuelId?: number;
    coefficientMontant?: number;
    appliquerReportMontant?: boolean;
    montantReport?: number;
    montantReportCalc?: number;
    montantTotal?: number;
    quittanceMensuelleSousLignes?: [IQuittanceMensuelleSousLigne];
}

export class QuittanceMensuelleLigne implements IQuittanceMensuelleLigne {
    constructor(
        public id?: number,
        public code?: string,
        public libelle?: string,
        public description?: string,
        public montantTotal?: number,
        public parent?: boolean,
        public child?: boolean,
        public parentQuittanceMensuelleLigneId?: number,
        public parentQuittanceMensuelleLigneCode?: string,
        public parentQuittanceMensuelleLigneLibelle?: string,
        public childQuittanceMensuelleLignes?: [IQuittanceMensuelleLigne],
        public quittanceMensuelleId?: number,
        public impotMensuelId?: number,
        public appliquerReportMontant?: boolean,
        public montantReport?: number,
        public montantReportCalc?: number,
        public coefficientMontant?: number,
        public quittanceMensuelleSousLignes?: [IQuittanceMensuelleSousLigne]
    ) {}
}
