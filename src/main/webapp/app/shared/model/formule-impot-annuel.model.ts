import { IImpotAnnuel } from 'app/shared/model/impot-annuel.model';

export interface IFormuleImpotAnnuel {
    id?: number;
    impotAnnuelParent?: IImpotAnnuel;
    impotAnnuelEnfant?: IImpotAnnuel;
    coefficient?: number;
}

export class FormuleImpotAnnuel implements IFormuleImpotAnnuel {
    constructor(
        public id?: number,
        public impotAnnuelParent?: IImpotAnnuel,
        public impotAnnuelEnfant?: IImpotAnnuel,
        public coefficient?: number
    ) {}
}
