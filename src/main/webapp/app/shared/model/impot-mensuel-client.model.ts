export interface IImpotMensuelClient {
    id?: number;
    applicable?: boolean;
    ficheClientId?: number;
    impotMensuelId?: number;
    impotMensuelParent?: boolean;
    impotMensuelChild?: boolean;
    impotMensuelParentId?: number;
}

export class ImpotMensuelClient implements IImpotMensuelClient {
    constructor(
        public id?: number,
        public applicable?: boolean,
        public ficheClientId?: number,
        public impotMensuelId?: number,
        impotMensuelParent?: boolean,
    impotMensuelChild?: boolean,
    impotMensuelParentId?: number
    ) {
        this.applicable = this.applicable || false;
    }
}

