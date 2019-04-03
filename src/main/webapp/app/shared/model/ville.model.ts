export interface IVille {
    id?: number;
    code?: string;
    codePostal?: string;
    libelle?: string;
    description?: string;
    regionId?: number;
    regionLibelle?: string;
}

export class Ville implements IVille {
    constructor(
        public id?: number,
        public code?: string,
        public codePostal?: string,
        public libelle?: string,
        public description?: string,
        public regionId?: number,
        public regionLibelle?: string
    ) {}
}
