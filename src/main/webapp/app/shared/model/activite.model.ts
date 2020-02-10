export interface IActivite {
    id?: number;
    code?: string;
    libelle?: string;
    description?: string;
}

export class Activite implements IActivite {
    constructor(public id?: number, public code?: string, public libelle?: string, public description?: string) {}
}
