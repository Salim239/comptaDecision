import {Moment} from 'moment';
import {IDeclarationAnnuelleDetail} from "app/shared/model/declaration-annuelle-detail.model";

export const enum TypeDeclaration {
    DECLARATION_INITIALE = 'DECLARATION_INITIALE',
    DECLARATION_RECTIFICATIVE = 'DECLARATION_RECTIFICATIVE'
}

export interface IDeclarationAnnuelle {
    id?: number;
    typeDeclaration?: TypeDeclaration;
    annee?: number;
    datePaiement?: Moment;
    numeroQuittance?: string;
    montant?: number;
    ficheClientId?: number;
    ficheClientDesignation?: string;
    ficheClientMatriculeFiscale?: string;
    ficheClientRegistreCommerce?: string;
    ficheClientDateCreation?: Moment;
    declarationAnnuelleDetails?: [IDeclarationAnnuelleDetail];
}

export class DeclarationAnnuelle implements IDeclarationAnnuelle {
    constructor(
        public id?: number,
        public typeDeclaration?: TypeDeclaration,
        public annee?: number,
        public datePaiement?: Moment,
        public numeroQuittance?: string,
        public montantChiffreAffaireHT?: number,
        public montantChiffreAffaireExport?: number,
        public montantChiffreAffaireLocal?: number,
        public montantChiffreAffaireTTC?: number,
        public montantResultatComptable?: number,
        public montantResultatFiscal?: number,
        public montantAutreDeduction?: number,
        public montantBaseImposable?: number,
        public montantImpotLiquide?: number,
        public montantAcompteProvisionnel?: number,
        public montantRetenueSource?: number,
        public montantNetAPaye?: number,
        public ficheClientId?: number,
        public ficheClientDesignation?: string,
        public ficheClientMatriculeFiscale?: string,
        public ficheClientRegistreCommerce?: string,
        public ficheClientDateCreation?: Moment,
        declarationAnnuelleDetails?: [IDeclarationAnnuelleDetail]
    ) {
    }
}
