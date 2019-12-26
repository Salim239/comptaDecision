import {IQuittanceMensuelleImpotSousDetail} from "app/shared/model/quittance-mensuelle-impot-sous-detail.model";

export interface IQuittanceMensuelleImpotDetail {
    id?: number;
    libelle?: string;
    parent?: boolean,
    child?: boolean,
    parentQuittanceMensuelleImpotDetailId?: number,
    parentQuittanceMensuelleImpotDetailLibelle?: string,
    childParentQuittanceMensuelleImpotDetails?: [IQuittanceMensuelleImpotDetail],
    quittanceMensuelleImpotId?: number;
    impotMensuelId?: number;
    impotMensuelLibelle?: string;
    impotMensuelCoefficientMontant?: number;
    impotMensuelAppliquerReportMontant?: boolean;
    montantTotalReport?: number,
    montantTotal?: number;
    quittanceMensuelleImpotSousDetails?: [IQuittanceMensuelleImpotSousDetail];
}

export class QuittanceMensuelleImpotDetail implements IQuittanceMensuelleImpotDetail {
    constructor(
        public id?: number,
        public libelle?: string,
        public montantTotal?: number,
        public parent?: boolean,
        public child?: boolean,
        public parentQuittanceMensuelleImpotDetailId?: number,
        public parentQuittanceMensuelleImpotDetailLibelle?: string,
        public childParentQuittanceMensuelleImpotDetails?: [IQuittanceMensuelleImpotDetail],
        public quittanceMensuelleImpotId?: number,
        public impotMensuelId?: number,
        public impotMensuelLibelle?: string,
        public impotMensuelAppliquerReportMontant?: boolean,
        public montantTotalReport?: number,
        public impotMensuelCoefficientMontant?: number,
        public quittanceMensuelleImpotSousDetails?: [IQuittanceMensuelleImpotSousDetail],
    ) {

    }
}

