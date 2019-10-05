import {Moment} from 'moment';
import {IQuittanceMensuelleImpotDetail} from "app/shared/model/quittance-mensuelle-impot-detail.model";

export const enum TypeDeclaration {
    DECLARATION_INITIALE = 'DECLARATION_INITIALE',
    DECLARATION_RECTIFICATIVE = 'DECLARATION_RECTIFICATIVE'
}

export interface IQuittanceMensuelleImpot {
    id?: number;
    annee?: number;
    mois?: number;
    typeDeclaration?: TypeDeclaration;
    numeroQuittance?: string;
    datePaiement?: Moment;
    montantPaye?: number;
    ficheClientId?: number;
    ficheClientDesignation?: string;
    ficheClientMatriculeFiscale?: string;
    ficheClientRegistreCommerce?: string;
    ficheClientDateCreation?: Moment;
    quittanceMensuelleImpotDetails?: IQuittanceMensuelleImpotDetail[];
}

export class QuittanceMensuelleImpot implements IQuittanceMensuelleImpot {
    constructor(
        public id?: number,
        public annee?: number,
        public mois?: number,
        public typeDeclaration?: TypeDeclaration,
        public numeroQuittance?: string,
        public datePaiement?: Moment,
        public montantPaye?: number,
        public ficheClientId?: number,
        public ficheClientDesignation?: string,
        public ficheClientMatriculeFiscale?: string,
        public ficheClientRegistreCommerce?: string,
        public ficheClientDateCreation?: Moment,
        public quittanceMensuelleImpotDetails?: IQuittanceMensuelleImpotDetail[]
    ) {
    }
}
