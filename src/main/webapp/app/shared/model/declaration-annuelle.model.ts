import {Moment} from 'moment';
import {IDeclarationAnnuelleDetail} from 'app/shared/model/declaration-annuelle-detail.model';
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

export interface IDeclarationAnnuelle {
    id?: number;
    typeDeclaration?: TypeDeclaration;
    statut?: StatutDeclaration;
    annee?: number;
    datePaiement?: Moment;
    numeroQuittance?: string;
    montantImpotAnnuel?: number;
    montantApPayes?: number;
    montantApPayesCalc?: number;
    montantRetenueSource?: number;
    montantReportAnterieur?: number;
    montantReportAnterieurCalc?: number;
    montantNet?: number;
    ficheClientId?: number;
    ficheClientDesignation?: string;
    ficheClientMatriculeFiscale?: string;
    ficheClientRegistreCommerce?: string;
    ficheClientDateCreation?: Moment;
    declarationAnnuelleDetails?: [IDeclarationAnnuelleDetail],
    businessAlerts?: BusinessAlert[];
}

export class DeclarationAnnuelle implements IDeclarationAnnuelle {
    constructor(
        public id?: number,
        public typeDeclaration?: TypeDeclaration,
        public statut?: StatutDeclaration,
        public annee?: number,
        public datePaiement?: Moment,
        public numeroQuittance?: string,
        public montantImpotAnnuel?: number,
        public montantApPayes?: number,
        public montantApPayesCalc?: number,
        public montantRetenueSource?: number,
        public montantReportAnterieur?: number,
        public montantReportAnterieurCalc?: number,
        public montantNet?: number,
        public ficheClientId?: number,
        public ficheClientDesignation?: string,
        public ficheClientMatriculeFiscale?: string,
        public ficheClientRegistreCommerce?: string,
        public ficheClientDateCreation?: Moment,
        declarationAnnuelleDetails?: [IDeclarationAnnuelleDetail],
        public businessAlerts?: BusinessAlert[]
    ) {}
}
