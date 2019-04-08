import { IImpotMensuelClient } from 'app/shared/model/impot-mensuel-client.model';

export interface IQuittanceMensuelleImpotLine {
    id?: number;
    montantPaye?: number;
    quittanceMensuelleImpotId?: number;
    quittanceMensuelleImpotMois?: string;
    quittanceMensuelleImpotAnnee?: string;
    impotMensuelClient?: IImpotMensuelClient;
}

export class QuittanceMensuelleImpotLine implements IQuittanceMensuelleImpotLine {
    constructor(
        public id?: number,
        public montantPaye?: number,
        public quittanceMensuelleImpotId?: number,
        public quittanceMensuelleImpotMois?: string,
        public quittanceMensuelleImpotAnnee?: string,
        public impotMensuelClient?: IImpotMensuelClient
    ) {}
}
