import { TypeValeur } from 'app/shared/model/impot-mensuel-ligne.model';

export interface IQuittanceMensuelleSousLigne {
    id?: number;
    quittanceMensuelleLigneId?: number;
    impotMensuelLigneId?: number;
    impotMensuelLigneCode?: string;
    impotMensuelLigneLibelle?: string;
    impotMensuelLigneTypeValeur?: TypeValeur;
    impotMensuelLigneValeur?: number;
    impotMensuelLigneValeurModifiable?: boolean;
    montantBase?: number;
    montantTotal?: number;
}

export class QuittanceMensuelleSousLigne implements IQuittanceMensuelleSousLigne {
    constructor(
        public id?: number,
        public quittanceMensuelleId?: number,
        public impotMensuelLigneId?: number,
        public impotMensuelLigneCode?: string,
        public impotMensuelLigneLibelle?: string,
        public impotMensuelLigneTypeValeur?: TypeValeur,
        public impotMensuelLigneValeur?: number,
        public impotMensuelLigneValeurModifiable?: boolean,
        public montantBase?: number,
        public montantTotal?: number
    ) {}
}
