import {Moment} from 'moment';
import {BusinessAlert} from 'app/shared/model/business-alert.model';

export const enum TypeCnss {
    CNSS_GENERALE = 'CNSS_GENERALE',
    CNSS_EMPLOYEUR = 'CNSS_EMPLOYEUR'
}

export const enum TypeDeclarationCnss {
    DECLARATION_INITIALE = 'DECLARATION_INITIALE',
    DECLARATION_COMPLEMENTAIRE = 'DECLARATION_COMPLEMENTAIRE'
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
    ficheClientId?: number;
    ficheClientCnssGerant?: string;
    ficheClientCnssEmployeur?: string;
    ficheClientDesignation?: string;
    ficheClientMatriculeFiscale?: string;
    ficheClientRegistreCommerce?: string;
    ficheClientDateCreation?: Moment;
    typeDeclaration?: TypeDeclarationCnss;
    tauxCnssNormal?: number;
    tauxCnssKarama?: number;
    tauxCnssKaramaAccident?: number;
    tauxCnssNormalAccident?: number;
    totalTauxCnssNormal?: number;
    totalTauxCnssKarama?: number;
    montantCnssNormal?: number;
    montantCnssKarama?: number;
    montantTotalCnss?: number;
    montantTotalSalaireBrut?: number;
    montantPenalite?: number;
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
        public ficheClientId?: number,
        public ficheClientDesignation?: string,
        public ficheClientMatriculeFiscale?: string,
        public ficheClientRegistreCommerce?: string,
        public ficheClientCnssGerant?: string,
        public ficheClientCnssEmployeur?: string,
        public ficheClientDateCreation?: Moment,
        public typeDeclaration?: TypeDeclarationCnss,
        public tauxCnssNormal?: number,
        public tauxCnssKarama?: number,
        public tauxCnssNormalAccident?: number,
        public tauxCnssKaramaAccident?: number,
        public montantCnssNormal?: number,
        public montantCnssKarama?: number,
        public montantTotalCnss?: number,
        public montantSalaireBrutNormal?: number,
        public montantSalaireBrutKarama?: number,
        public montantTotalSalaireBrut?: number,
        public totalTauxCnssNormal?: number,
        public totalTauxCnssKarama?: number,
        public montantPenalite?: number,
        public businessAlerts?: BusinessAlert[]
    ) {
        this.montantPenalite = 0;
    }
}
