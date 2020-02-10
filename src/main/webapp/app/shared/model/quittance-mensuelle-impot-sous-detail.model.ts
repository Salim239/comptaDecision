import {TypeValeur} from "app/shared/model/impot-mensuel-detail.model";

export interface IQuittanceMensuelleImpotSousDetail {
    id?: number;
    quittanceMensuelleImpotDetailId?: number;
    impotMensuelDetailId?: number;
    impotMensuelDetailCode?: string;
    impotMensuelDetailLibelle?: string;
    impotMensuelDetailTypeValeur?: TypeValeur;
    impotMensuelDetailValeur?: number;
    impotMensuelDetailValeurModifiable?: boolean;
    montantBase?: number;
    montantTotal?: number;
}

export class QuittanceMensuelleImpotSousDetail implements IQuittanceMensuelleImpotSousDetail {
    constructor(
        public id?: number,
        public quittanceMensuelleImpotId?: number,
        public impotMensuelDetailId?: number,
        public impotMensuelDetailCode?: string,
        public impotMensuelDetailLibelle?: string,
        public impotMensuelDetailTypeValeur?: TypeValeur,
        public impotMensuelDetailValeur?: number,
        public impotMensuelDetailValeurModifiable?: boolean,
        public montantBase?: number,
        public montantTotal?: number
    ) {}
}

