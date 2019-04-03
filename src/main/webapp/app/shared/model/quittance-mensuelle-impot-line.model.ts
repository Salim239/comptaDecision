import { IImpotMensuelClient } from 'app/shared/model/impot-mensuel-client.model';

export interface IQuittanceMensuelleImpotLine {
    id?: number;
    montantPaye?: number;
    impotMensuelClients?: IImpotMensuelClient[];
    quittanceMensuelleImpotId?: number;
}

export class QuittanceMensuelleImpotLine implements IQuittanceMensuelleImpotLine {
    constructor(
        public id?: number,
        public montantPaye?: number,
        public impotMensuelClients?: IImpotMensuelClient[],
        public quittanceMensuelleImpotId?: number
    ) {}
}
