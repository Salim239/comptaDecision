import { IImpotAnnuelDetail } from 'app/shared/model/impot-annuel-detail.model';

export interface IImpotAnnuel {
    id?: number;
    code?: string;
    libelle?: string;
    description?: string;
    calcule?: boolean;
    triOrdre?: number;
    impotAnnuelDetails?: IImpotAnnuelDetail[];
}

export class ImpotAnnuel implements IImpotAnnuel {
    constructor(
        public id?: number,
        public code?: string,
        public libelle?: string,
        public description?: string,
        public calcule?: boolean,
        public triOrdre?: number,
        public impotAnnuelDetails?: IImpotAnnuelDetail[]
    ) {}
}
