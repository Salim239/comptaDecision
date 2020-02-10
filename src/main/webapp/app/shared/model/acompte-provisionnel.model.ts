import { Moment } from 'moment';

export interface IAcompteProvisionnel {
    id?: number;
    annee?: number;
    numero?: number;
    date?: Moment;
    numeroQuittance?: string;
    montantBase?: number;
    montantAcompteProvisionnel?: number;
    montantReportAnterieur?: number;
    montantRetenueSource?: number;
    montantNet?: number;
    ficheClientId?: number;
    ficheClientDesignation?: string;
    ficheClientMatriculeFiscale?: string;
    ficheClientRegistreCommerce?: string;
    ficheClientDateCreation?: Moment;
}

export class AcompteProvisionnel implements IAcompteProvisionnel {
    constructor(
        public id?: number,
        public annee?: number,
        public numero?: number,
        public date?: Moment,
        public numeroQuittance?: string,
        public montantBase?: number,
        public montantAcompteProvisionnel?: number,
        public montantReportAnterieur?: number,
        public montantRetenueSource?: number,
        public montantNet?: number,
        public ficheClientId?: number,
        public ficheClientDesignation?: string,
    public ficheClientMatriculeFiscale?: string,
    public ficheClientRegistreCommerce?: string,
    public ficheClientDateCreation?: Moment
    ) {}
}
