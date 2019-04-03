export interface IImpotMensuelClient {
    id?: number;
    mois?: number;
    applicable?: boolean;
    ficheClientId?: number;
    ficheClientDesignation?: string;
    ficheClientMatriculeFiscale?: string;
    ficheClientRegistreCommerce?: string;
    impotMensuelId?: number;
    impotMensuelLibelle?: string;
    impotMensuelDescription?: string;
}

export class ImpotMensuelClient implements IImpotMensuelClient {
    constructor(
        public id?: number,
        public mois?: number,
        public applicable?: boolean,
        public ficheClientId?: number,
        public ficheClientDesignation?: string,
        public ficheClientMatriculeFiscale?: string,
        public ficheClientRegistreCommerce?: string,
        public impotMensuelId?: number,
        public impotMensuelLibelle?: string,
        public impotMensuelDescription?: string,
) {
    this.applicable = this.applicable || false;
}
}

