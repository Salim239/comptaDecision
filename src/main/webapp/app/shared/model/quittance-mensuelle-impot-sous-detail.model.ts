import {IImpotMensuelClient} from 'app/shared/model/impot-mensuel-client.model';
import {IImpotMensuelDetail} from "app/shared/model/impot-mensuel-detail.model";

export interface IQuittanceMensuelleImpotSousDetail {
    id?: number;
    quittanceMensuelleImpotDetailId?: number;
    impotMensuelDetail?: IImpotMensuelDetail;
    montantBase?: number;
    // montantTotal?: number;
}

export class QuittanceMensuelleImpotSousDetail implements IQuittanceMensuelleImpotSousDetail {
    constructor(
        public id?: number,
        public quittanceMensuelleImpotId?: number,
        public impotMensuelDetail?: IImpotMensuelDetail,
        public impotMensuelClient?: IImpotMensuelClient,
        public montantBase?: number,
        // public montantTotal?: number
    ) {}
}

