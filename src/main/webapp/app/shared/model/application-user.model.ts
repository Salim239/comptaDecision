export interface IApplicationUser {
    id?: number;
    matricule?: string;
    poste?: string;
    dateEmbauche?: string;
    cabinetComptableCode?: string;
    cabinetComptableId?: number;
}

export class ApplicationUser implements IApplicationUser {
    constructor(
        public id?: number,
        public matricule?: string,
        public poste?: string,
        public dateEmbauche?: string,
        public cabinetComptableCode?: string,
        public cabinetComptableId?: number
    ) {}
}
