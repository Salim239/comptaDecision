export interface ISecteurActivite {
    id?: number;
    code?: string;
    libelle?: string;
    description?: string;
}

export class SecteurActivite implements ISecteurActivite {
    constructor(public id?: number, public code?: string, public libelle?: string, public description?: string) {}
}
