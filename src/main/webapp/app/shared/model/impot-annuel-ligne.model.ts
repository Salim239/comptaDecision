export interface IImpotAnnuelLigne {
    id?: number;
    impotAnnuelId?: number;
    impotAnnuelLibelle?: string;
    impotMensuelId?: number;
    impotMensuelLibelle?: string;
    impotMensuelLigneId?: number;
    impotMensuelLigneLibelle?: string;
    coefficient?: number;
}

export class ImpotAnnuelLigne implements IImpotAnnuelLigne {
    constructor(
        public id?: number,
        public impotAnnuelId?: number,
        public impotAnnuelLibelle?: string,
        public impotMensuelId?: number,
        public impotMensuelLibelle?: string,
        public impotMensuelLigneId?: number,
        public impotMensuelLigneLibelle?: string,
        public coefficient?: number
    ) {}
}
