import {Moment} from 'moment';
import {IQuittanceMensuelleImpotLine} from "app/shared/model/quittance-mensuelle-impot-line.model";

export interface IQuittanceMensuelleImpot {
    id?: number;
    annee?: number;
    mois?: number;
    numeroQuittance?: string;
    datePaiement?: Moment;
    montantPaye?: number;
    ficheClientId?: number;
    ficheClientDesignation?: string;
    ficheClientMatriculeFiscale?: string;
    ficheClientRegistreCommerce?: string;
    quittanceMensuelleImpotLines?: IQuittanceMensuelleImpotLine[];
}

export class QuittanceMensuelleImpot implements IQuittanceMensuelleImpot {
    constructor(
        public id?: number,
        public annee?: number,
        public mois?: number,
        public numeroQuittance?: string,
        public datePaiement?: Moment,
        public montantPaye?: number,
        public ficheClientId?: number,
        public ficheClientDesignation?: string,
        public ficheClientMatriculeFiscale?: string,
        public ficheClientRegistreCommerce?: string,
        public quittanceMensuelleImpotLines?: IQuittanceMensuelleImpotLine[]
    ) {
    }
}
