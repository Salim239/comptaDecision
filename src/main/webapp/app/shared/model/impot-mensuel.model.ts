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
    childImpotMensuels?: IImpotMensuel[];
}

export class ImpotMensuel implements IImpotMensuel {
    constructor(public id?: number, public code?: string, public libelle?: string,
                public parent?: boolean, public child?: boolean, public description?: string,
                public parentImpotMensuelId?: number,
                public parentImpotMensuelLibelle?: string,
                public impotMensuelDetails?: IImpotMensuelDetail[],
        public childImpotMensuels?: IImpotMensuel[]) {
        this.impotMensuelDetails = [];
        this.childImpotMensuels = [];
    }
}
