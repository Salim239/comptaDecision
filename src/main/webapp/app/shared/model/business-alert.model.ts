export const enum TypeAlert {
    ERROR, WARNING, INFO, SUCCESS
}

export const enum CodeAlert  {
    WARNING_QUITTANCE_PRECEDENTE_INEXISTANTE,
    WARNING_DECLARATION_ANNUELLE_PRECEDENTE_INNEXISTANTE,
    WARNING_QUITTANCE_PRECEDENTE_NON_VALIDE,
    WARNING_DECLARATION_ANNUELLE_PRECEDENTE_NON_VALIDE,
    WARNING_QUITTANCE_INITIALE_NON_VALIDE,
    WARNING_DECLARATION_ANNUELLE_INITIALE_NON_VALIDE
}

export interface IBusinessAlert {
    type?: TypeAlert;
    code?: CodeAlert;
    params?: any[];
}

export class BusinessAlert implements IBusinessAlert {
    constructor(
        public type?: TypeAlert,
        public code?: CodeAlert,
        public params?: any[]
    ) {
    }
}
