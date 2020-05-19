import {Moment} from 'moment';
import {IQuittanceMensuelleImpotDetail} from 'app/shared/model/quittance-mensuelle-impot-detail.model';
import {BusinessAlert} from "app/shared/model/business-alert.model";

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

export interface IQuittanceMensuelleImpot {
    id?: number;
    annee?: number;
    mois?: number;
    typeDeclaration?: TypeDeclaration;
    statut?: StatutDeclaration;
    numeroQuittance?: string;
    datePaiement?: Moment;
    montantTotal?: number;
    ficheClientId?: number;
    ficheClientDesignation?: string;
    ficheClientMatriculeFiscale?: string;
    ficheClientRegistreCommerce?: string;
    ficheClientDateCreation?: Moment;
    quittanceMensuelleImpotDetails?: IQuittanceMensuelleImpotDetail[];
    businessAlerts?: BusinessAlert[];

}

export class QuittanceMensuelleImpot implements IQuittanceMensuelleImpot {
    constructor(
        public id?: number,
        public annee?: number,
        public mois?: number,
        public typeDeclaration?: TypeDeclaration,
        public statut?: StatutDeclaration,
        public numeroQuittance?: string,
        public datePaiement?: Moment,
        public montantTotal?: number,
        public ficheClientId?: number,
        public ficheClientDesignation?: string,
        public ficheClientMatriculeFiscale?: string,
        public ficheClientRegistreCommerce?: string,
        public ficheClientDateCreation?: Moment,
        public quittanceMensuelleImpotDetails?: IQuittanceMensuelleImpotDetail[],
        public parentQuittanceId?: number,
        public businessAlerts?: BusinessAlert[]
    ) {}
}
