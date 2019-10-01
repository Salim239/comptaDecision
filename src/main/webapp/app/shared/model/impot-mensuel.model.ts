import {IImpotMensuelDetail} from "app/shared/model/impot-mensuel-detail.model";

export interface IImpotMensuel {
    id?: number;
    code?: string;
    libelle?: string;
    description?: string;
    child?: boolean;
    parent?: boolean;
    parentImpotMensuelId?: number;
    parentImpotMensuelLibelle?: string;
    impotMensuelDetails?: IImpotMensuelDetail[];
}

export class ImpotMensuel implements IImpotMensuel {
    constructor(public id?: number, public code?: string, public libelle?: string,
                public parent?: boolean, public child?: boolean, public parentImpotMensuelId?: number,
        public parentImpotMensuelLibelle?: string, public impotMensuelDetails?: IImpotMensuelDetail[], public description?: string) {
    }
}
