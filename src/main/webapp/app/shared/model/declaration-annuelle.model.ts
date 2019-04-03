import {Moment} from 'moment';

export interface IDeclarationAnnuelle {
    id?: number;
    annee?: number;
    datePaiement?: Moment;
    numeroQuittance?: string;
    montantChiffreAffaireHT?: number;
    montantChiffreAffaireExport?: number;
    montantChiffreAffaireImpot?: number;
    montantChiffreAffaireTTC?: number;
    montantResultatComptable?: number;
    montantDeductionCommune?: number;
    montantAutreDeduction?: number;
    montantBaseImposable?: number;
    montantImpotLiquide?: number;
    montantAcompteProvisionnel?: number;
    montantRetenueSource?: number;
    montantNetAPaye?: number;
    ficheClientId?: number;
    ficheClientDesignation?: string;
    ficheClientMatriculeFiscale?: string;
    ficheClientRegistreCommerce?: string;
}

export class DeclarationAnnuelle implements IDeclarationAnnuelle {
    constructor(
        public id?: number,
        public annee?: number,
        public datePaiement?: Moment,
        public numeroQuittance?: string,
        public montantChiffreAffaireHT?: number,
        public montantChiffreAffaireExport?: number,
        public montantChiffreAffaireImpot?: number,
        public montantChiffreAffaireTTC?: number,
        public montantResultatComptable?: number,
        public montantDeductionCommune?: number,
        public montantAutreDeduction?: number,
        public montantBaseImposable?: number,
        public montantImpotLiquide?: number,
        public montantAcompteProvisionnel?: number,
        public montantRetenueSource?: number,
        public montantNetAPaye?: number,
        public ficheClientId?: number,
        public ficheClientDesignation?: string,
        public ficheClientMatriculeFiscale?: string,
        public ficheClientRegistreCommerce?: string
    ) {
    }
}
