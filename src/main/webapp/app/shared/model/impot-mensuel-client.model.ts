export interface IImpotMensuelClient {
    id?: number;
    applicable?: boolean;
    ficheClientId?: number;
    impotMensuelId?: number;
}

export class ImpotMensuelClient implements IImpotMensuelClient {
    constructor(
        public id?: number,
        public applicable?: boolean,
        public ficheClientId?: number,
        public impotMensuelId?: number
    ) {
        this.applicable = this.applicable || false;
    }
}

