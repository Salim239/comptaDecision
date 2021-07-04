export interface IDeclarationAnnuelleLigne {
    id?: number;
    code?: string;
    triOrdre?: number;
    calcule?: boolean;
    montant?: number;
    montantCalcule?: number;
    montantNegatifAccepte?: boolean;
    libelle?: string;
    description?: string;
    impotAnnuelId?: number;
    declarationAnnuelleId?: number;
}

export class DeclarationAnnuelleLigne implements IDeclarationAnnuelleLigne {
    constructor(
        public id?: number,
        public code?: string,
        public triOrdre?: number,
        public sumAcomptesPrevisonnels?: boolean,
        public montant?: number,
        public montantCalcule?: number,
        public libelle?: string,
        public montantNegatifAccepte?: boolean,
        public description?: string,
        public impotAnnuelId?: number,
        public declarationAnnuelleId?: number
    ) {}
}
