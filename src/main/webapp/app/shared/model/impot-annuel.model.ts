import { IImpotAnnuelLigne } from 'app/shared/model/impot-annuel-ligne.model';

export interface IImpotAnnuel {
    id?: number;
    code?: string;
    libelle?: string;
    description?: string;
    calcule?: boolean;
    triOrdre?: number;
    impotAnnuelLignes?: IImpotAnnuelLigne[];
}

export class ImpotAnnuel implements IImpotAnnuel {
    constructor(
        public id?: number,
        public code?: string,
        public libelle?: string,
        public description?: string,
        public calcule?: boolean,
        public triOrdre?: number,
        public impotAnnuelLignes?: IImpotAnnuelLigne[]
    ) {}
}
