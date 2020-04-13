export interface IImpotAnnuelDetail {
    id?: number;
    impotMensuelId?: number;
    impotMensuelLibelle?: string;
    impotMensuelDetailId?: number;
    impotMensuelDetailLibelle?: string;
    coefficient?: number;
}

export class ImpotAnnuelDetail implements IImpotAnnuelDetail {
    constructor(
        public id?: number,
        public impotMensuelId?: number,
        public impotMensuelLibelle?: string,
        public impotMensuelDetailId?: number,
        public impotMensuelDetailLibelle?: string,
        public coefficient?: number
    ) {
    }
}
