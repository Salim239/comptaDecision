import { Moment } from 'moment';
import { BusinessAlert } from 'app/shared/model/business-alert.model';

export const enum TypeCnss {
    CNSS_GENERALE = 'CNSS_GENERALE',
    CNSS_EMPLOYEUR = 'CNSS_EMPLOYEUR'
}

export const enum TypeDeclarationCnss {
    DECLARATION_INITALE,
    DECLARATION_COMPLEMENTAIRE
}

export interface ICnss {
    id?: number;
    typeCnss?: TypeCnss;
    annee?: number;
    trimestre?: number;
    date?: Moment;
    numeroQuittance?: string;
    montantSalaireBrutNormal?: number;
    montantSalaireBrutKarama?: number;
    montantSalaireBrutAutre?: number;
    montantTotal?: number;
    cnss?: number;
    ficheClientId?: number;
    ficheClientDesignation?: string;
    ficheClientMatriculeFiscale?: string;
    ficheClientRegistreCommerce?: string;
    ficheClientDateCreation?: Moment;
    typeDeclaration?: TypeDeclarationCnss;
    tauxCnssNormal?: number;
    tauxCnssKarama?: number;
    tauxCnssAccident?: number;
    tauxCnssAutre?: number;
    montantCnssNormal?: number;
    montantCnssKarama?: number;
    montantCnssAutre?: number;
    businessAlerts?: BusinessAlert[];
}

export class Cnss implements ICnss {
    constructor(
        public id?: number,
        public typeCnss?: TypeCnss,
        public annee?: number,
        public trimestre?: number,
        public date?: Moment,
        public numeroQuittance?: string,
        public montantSalaireBrutNormal?: number,
        public montantSalaireBrutKarama?: number,
        public montantSalaireBrutAutre?: number,
        public montantTotal?: number,
        public cnss?: number,
        public ficheClientId?: number,
        public ficheClientDesignation?: string,
        public ficheClientMatriculeFiscale?: string,
        public ficheClientRegistreCommerce?: string,
        public ficheClientDateCreation?: Moment,
        public typeDeclaration?: TypeDeclarationCnss,
        public tauxCnssNormal?: number,
        public tauxCnssKarama?: number,
        public tauxCnssAccident?: number,
        public tauxCnssAutre?: number,
        public montantCnssNormal?: number,
        public montantCnssKarama?: number,
        public montantCnssAutre?: number,
        public businessAlerts?: BusinessAlert[]
    ) {}
}
