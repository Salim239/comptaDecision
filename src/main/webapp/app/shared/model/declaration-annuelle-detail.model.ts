export interface IDeclarationAnnuelleDetail {
    id?: number;
    code?: string;
    triOrdre?: number;
    calcule?: boolean;
    montant?: number;
    montantCalcule?: number;
    libelle?: string;
    description?: string;
    impotAnnuelId?: number;
    declarationAnnuelleId?: number;
}

export class DeclarationAnnuelleDetail implements IDeclarationAnnuelleDetail {
    constructor(
    public id?: number,
    public code?: string,
    public triOrdre?: number,
    public calcule?: boolean,
    public montant?: number,
    public montantCalcule?: number,
    public libelle?: string,
    public description?: string,
    public impotAnnuelId?: number,
    public declarationAnnuelleId?: number
    ) {
    }
}
