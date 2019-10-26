import {IImpotMensuel} from "app/shared/model/impot-mensuel.model";

export interface IQuittanceMensuelleImpotDetail {
    id?: number;
    quittanceMensuelleImpotId?: number;
    impotMensuel?: IImpotMensuel;
    montantTotal?: number;
}

export class QuittanceMensuelleImpotDetail implements IQuittanceMensuelleImpotDetail {
    constructor(
        public id?: number,
        public quittanceMensuelleImpotId?: number,
        public impotMensuel?: IImpotMensuel,
        public montantTotal?: number
    ) {}
}

