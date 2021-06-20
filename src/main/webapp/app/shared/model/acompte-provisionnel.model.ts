import { Moment } from 'moment';
import { BusinessAlert } from 'app/shared/model/business-alert.model';

export const enum TypeDeclaration {
    DECLARATION_INITIALE = 'DECLARATION_INITIALE',
    DECLARATION_RECTIFICATIVE = 'DECLARATION_RECTIFICATIVE'
}

export const enum StatutDeclaration {
    BROULLON = 'BROUILLON',
    VALIDE = 'VALIDE',
    ARCHIVE = 'ARCHIVE',
    RECTIFIE = 'RECTIFIE'
}

export interface IAcompteProvisionnel {
    id?: number;
    annee?: number;
    numero?: number;
    date?: Moment;
    numeroQuittance?: string;
    montantBase?: number;
    montantBaseCalc?: number;
    montantAcompteProvisionnel?: number;
    montantReportAnterieur?: number;
    montantReportAnterieurCalc?: number;
    montantRetenueSource?: number;
    montantNet?: number;
    ficheClientId?: number;
    ficheClientDesignation?: string;
    ficheClientMatriculeFiscale?: string;
    ficheClientRegistreCommerce?: string;
    ficheClientDateCreation?: Moment;
    type?: TypeDeclaration;
    statut?: StatutDeclaration;
    businessAlerts?: BusinessAlert[];
}

export class AcompteProvisionnel implements IAcompteProvisionnel {
    constructor(
        public id?: number,
        public annee?: number,
        public numero?: number,
        public date?: Moment,
        public numeroQuittance?: string,
        public montantBase?: number,
        public montantBaseCalc?: number,
        public montantAcompteProvisionnel?: number,
        public montantReportAnterieur?: number,
        public montantReportAnterieurCalc?: number,
        public montantRetenueSource?: number,
        public montantNet?: number,
        public ficheClientId?: number,
        public ficheClientDesignation?: string,
        public ficheClientMatriculeFiscale?: string,
        public ficheClientRegistreCommerce?: string,
        public ficheClientDateCreation?: Moment,
        public type?: TypeDeclaration,
        public statut?: StatutDeclaration,
        public businessAlerts?: BusinessAlert[]
    ) {}
}
