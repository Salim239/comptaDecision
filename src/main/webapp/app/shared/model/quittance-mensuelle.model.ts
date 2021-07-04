import { Moment } from 'moment';
import { IQuittanceMensuelleLigne } from 'app/shared/model/quittance-mensuelle-ligne.model';
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

export interface IQuittanceMensuelle {
    id?: number;
    annee?: number;
    mois?: number;
    typeDeclaration?: TypeDeclaration;
    statut?: StatutDeclaration;
    numeroQuittance?: string;
    datePaiement?: Moment;
    montantTotal?: number;
    montantTaxes?: number;
    montantPenalite?: number;
    ficheClientId?: number;
    ficheClientDesignation?: string;
    ficheClientMatriculeFiscale?: string;
    ficheClientRegistreCommerce?: string;
    ficheClientDateCreation?: Moment;
    quittanceMensuelleLignes?: IQuittanceMensuelleLigne[];
    businessAlerts?: BusinessAlert[];
}

export class QuittanceMensuelle implements IQuittanceMensuelle {
    constructor(
        public id?: number,
        public annee?: number,
        public mois?: number,
        public typeDeclaration?: TypeDeclaration,
        public statut?: StatutDeclaration,
        public numeroQuittance?: string,
        public datePaiement?: Moment,
        public montantTotal?: number,
        public montantTaxes?: number,
        public montantPenalite?: number,
        public ficheClientId?: number,
        public ficheClientDesignation?: string,
        public ficheClientMatriculeFiscale?: string,
        public ficheClientRegistreCommerce?: string,
        public ficheClientDateCreation?: Moment,
        public quittanceMensuelleLignes?: IQuittanceMensuelleLigne[],
        public parentQuittanceId?: number,
        public businessAlerts?: BusinessAlert[]
    ) {
        this.montantPenalite = 0;
    }
}
