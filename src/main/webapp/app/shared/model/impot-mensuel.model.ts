export interface IImpotMensuel {
    id?: number;
    code?: string;
    libelle?: string;
    description?: string;
}

export class ImpotMensuel implements IImpotMensuel {
    constructor(public id?: number, public code?: string, public libelle?: string, public description?: string) {}
}
