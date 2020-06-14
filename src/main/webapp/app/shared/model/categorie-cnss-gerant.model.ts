export interface ICategorieCnssGerant {
    id?: number;
    code?: string;
    libelle?: string;
    description?: string;
    montantCotisationCnss?: number;
}

export class CategorieCnssGerant implements ICategorieCnssGerant {
    constructor(
        public id?: number,
        public code?: string,
        public libelle?: string,
        public description?: string,
        public montantCotisationCnss?: number
    ) {}
}
