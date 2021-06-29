export const enum TypeValeur {
    TAUX = 'TAUX',
    MONTANT = 'MONTANT'
}

export interface IImpotMensuelLigne {
    id?: number;
    triOrdre?: number;
    code?: string;
    libelle?: string;
    typeValeur?: TypeValeur;
    valeurModifiable?: boolean;
    valeur?: number;
    description?: string;
    dateDebut?: Date;
    dateFin?: Date;
}

export class ImpotMensuelLigne implements IImpotMensuelLigne {
    constructor(
        public id?: number,
        public triOrdre?: number,
        public code?: string,
        public libelle?: string,
        public typeValeur?: TypeValeur,
        public valeurModifiable?: boolean,
        public valeur?: number,
        public description?: string,
        public dateDebut?: Date,
        public dateFin?: Date
    ) {
        this.valeurModifiable = false;
        this.typeValeur = TypeValeur.TAUX;
    }
}
