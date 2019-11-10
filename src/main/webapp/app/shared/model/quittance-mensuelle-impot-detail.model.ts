import {IImpotMensuel} from "app/shared/model/impot-mensuel.model";
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
    impotMensuel?: IImpotMensuel;
    montantTotal?: number;
    quittanceMensuelleImpotSousDetails?: [IQuittanceMensuelleImpotSousDetail];
}

export class QuittanceMensuelleImpotDetail implements IQuittanceMensuelleImpotDetail {
    constructor(
        public id?: number,
        public libelle?: string,
        public parent?: boolean,
        public child?: boolean,
        public parentQuittanceMensuelleImpotDetailId?: number,
        public parentQuittanceMensuelleImpotDetailLibelle?: string,
        public childParentQuittanceMensuelleImpotDetails?: [IQuittanceMensuelleImpotDetail],
        public quittanceMensuelleImpotId?: number,
        public impotMensuel?: IImpotMensuel,
        public montantTotal?: number,
        public quittanceMensuelleImpotSousDetails?: [IQuittanceMensuelleImpotSousDetail],
    ) {}
}

