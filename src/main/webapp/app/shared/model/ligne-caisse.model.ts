export interface ILigneCaisse {
    id?: number;
    typeOperation?: number;
    libelle?: string;
    montantOperation?: number;
    quittanceMensuelleId?: number;
    declarationAnnuelleId?: number;
    cnssId?: number;
    caisseId?: number;
}

export class LigneCaisse implements ILigneCaisse {
    constructor(
        public id?: number,
        public typeOperation?: number,
        public libelle?: string,
        public montantOperation?: number,
        public quittanceMensuelleId?: number,
        public declarationAnnuelleId?: number,
        public cnssId?: number,
        public caisseId?: number
    ) {}
}
