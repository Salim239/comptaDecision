import {IImpotMensuelClient} from 'app/shared/model/impot-mensuel-client.model';
import {IImpotMensuelDetail, TypeValeur} from "app/shared/model/impot-mensuel-detail.model";
import {IQuittanceMensuelleImpot} from "app/shared/model/quittance-mensuelle-impot.model";

export interface IQuittanceMensuelleImpotDetail {
    id?: number;
    quittanceMensuelleImpot?: IQuittanceMensuelleImpot;
    impotMensuelDetail?: IImpotMensuelDetail;
    impotMensuelClient?: IImpotMensuelClient;
    montantBase?: number;
    impotMensuelValeur?: number;
    impotMensuelTypeValeur?: TypeValeur;
    montantTotal?: number;
}

export class QuittanceMensuelleImpotDetail implements IQuittanceMensuelleImpotDetail {
    constructor(
        public id?: number,
        public quittanceMensuelleImpot?: IQuittanceMensuelleImpot,
        public impotMensuelDetail?: IImpotMensuelDetail,
        public impotMensuelClient?: IImpotMensuelClient,
        public montantBase?: number,
        public impotMensuelValeur?: number,
        public impotMensuelTypeValeur?: TypeValeur,
        public montantTotal?: number
    ) {}
}
