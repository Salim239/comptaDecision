import {Moment} from 'moment';

export interface ICnss {
    id?: number;
    annee?: number;
    trimestre?: number;
    date?: Moment;
    numeroQuittance?: string;
    montantSalaireBrutNormal?: number;
    montantSalaireBrutKarama?: number;
    montantSalaireBrutAutre?: number;
    montantChiffreAffaireTTC?: number;
    tot?: number;
    cnss?: number;
    ficheClientId?: number;
    ficheClientDesignation?: string;
    ficheClientMatriculeFiscale?: string;
    ficheClientRegistreCommerce?: string;
}

export class Cnss implements ICnss {
    constructor(
        public id?: number,
        public annee?: number,
        public trimestre?: number,
        public date?: Moment,
        public numeroQuittance?: string,
        public montantSalaireBrutNormal?: number,
        public montantSalaireBrutKarama?: number,
        public montantSalaireBrutAutre?: number,
        public montantChiffreAffaireTTC?: number,
        public tot?: number,
        public cnss?: number,
        public ficheClientId?: number,
        public ficheClientDesignation?: string,
        public ficheClientMatriculeFiscale?: string,
        public ficheClientRegistreCommerce?: string
    ) {
    }
}
