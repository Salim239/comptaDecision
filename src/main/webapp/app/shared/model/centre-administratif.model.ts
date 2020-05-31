export const enum TypeCentreAdministratif {
    administration_cnss = 'administration_cnss',
    administration_fiscale = 'administration_fiscale',
    administration_impot = 'administration_impot'
}

export interface ICentreAdministratif {
    id?: number;
    type?: TypeCentreAdministratif;
    libelle?: string;
    telephone1?: string;
    telephone2?: string;
    telephone3?: string;
    email1?: string;
    email2?: string;
    email3?: string;
    fax?: string;
    rib?: string;
    banque?: string;
    adresse?: string;
    villeId?: number;
    villeLibelle?: string;
    regionId?: number;
    regionLibelle?: string;
    codePostal?: string;
    description?: string;
}

export class CentreAdministratif implements ICentreAdministratif {
    constructor(
        public id?: number,
        public type?: TypeCentreAdministratif,
        public libelle?: string,
        public telephone1?: string,
        public telephone2?: string,
        public telephone3?: string,
        public email1?: string,
        public email2?: string,
        public email3?: string,
        public fax?: string,
        public rib?: string,
        public banque?: string,
        public adresse?: string,
        public villeId?: number,
        public villeLibelle?: string,
        public regionId?: number,
        public regionLibelle?: string,
        public codePostal?: string,
        public description?: string
    ) {}
}
