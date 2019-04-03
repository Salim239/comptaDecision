export interface IRegion {
    id?: number;
    code?: string;
    libelle?: string;
    description?: string;
}

export class Region implements IRegion {
    constructor(public id?: number, public code?: string, public libelle?: string, public description?: string) {}
}
