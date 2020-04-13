
export const enum TypeValeur {
    TAUX = 'TAUX',
    MONTANT = 'MONTANT'
}

export interface IImpotMensuelDetail {
    id?: number;
    triOrdre?: number;
    code?: string;
    libelle?: string;
    typeValeur?: TypeValeur;
    valeurModifiable?: boolean;
    valeur?: number;
    description?: string;
}

export class ImpotMensuelDetail implements IImpotMensuelDetail {
    constructor(
        public id?: number,
        public triOrdre?: number,
        public code?: string,
        public libelle?: string,
        public typeValeur?: TypeValeur,
        public valeurModifiable?: boolean,
        public valeur?: number,
        public description?: string,
    ) {
        this.valeurModifiable = false;
        this.typeValeur = TypeValeur.TAUX;
    }
}
