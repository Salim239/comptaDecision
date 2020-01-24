import {IQuittanceMensuelleImpotSousDetail} from "app/shared/model/quittance-mensuelle-impot-sous-detail.model";

export interface IQuittanceMensuelleImpotDetail {
    id?: number;
    code?: string;
    libelle?: string;
    description?: string;
    parent?: boolean,
    child?: boolean,
    parentQuittanceMensuelleImpotDetailId?: number,
    parentQuittanceMensuelleImpotDetailCode?: string,
    parentQuittanceMensuelleImpotDetailLibelle?: string,
    childParentQuittanceMensuelleImpotDetails?: [IQuittanceMensuelleImpotDetail],
    quittanceMensuelleImpotId?: number;
    impotMensuelId?: number;
    coefficientMontant?: number;
    appliquerReportMontant?: boolean;
    montantReport?: number,
    montantTotal?: number;
    quittanceMensuelleImpotSousDetails?: [IQuittanceMensuelleImpotSousDetail];
}

export class QuittanceMensuelleImpotDetail implements IQuittanceMensuelleImpotDetail {
    constructor(
        public id?: number,
        public code?: string,
        public libelle?: string,
        public description?: string,
        public montantTotal?: number,
        public parent?: boolean,
        public child?: boolean,
        public parentQuittanceMensuelleImpotDetailId?: number,
        public parentQuittanceMensuelleImpotDetailCode?: string,
        public parentQuittanceMensuelleImpotDetailLibelle?: string,
        public childParentQuittanceMensuelleImpotDetails?: [IQuittanceMensuelleImpotDetail],
        public quittanceMensuelleImpotId?: number,
        public impotMensuelId?: number,
        public appliquerReportMontant?: boolean,
        public montantReport?: number,
        public coefficientMontant?: number,
        public quittanceMensuelleImpotSousDetails?: [IQuittanceMensuelleImpotSousDetail],
    ) {

    }
}

