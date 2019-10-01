export interface IImpotMensuelDetail {
    id?: number;
    triOrdre: number;
    code: string;
    libelle: string;
    taux?: number;
    description?: string;
}

export class ImpotMensuelDetail implements IImpotMensuelDetail {
    constructor(
        public id: number,
        public triOrdre: number,
        public code: string,
        public libelle: string,
        public taux?: number,
        public description?: string,
    ) {
    }
}