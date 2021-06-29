import { Moment } from 'moment';

export const enum ModePaiement {
    CHEQUE = 'CHEQUE',
    VIREMENT = 'VIREMENT',
    CAISSE = 'CAISSE',
    ESPECE = 'ESPECE'
}

export interface IPaiement {
    id?: number;
    libelle?: string;
    modePaiement?: ModePaiement;
    numeroPreuvePaiement?: string;
    datePaiement?: Moment;
    montantPaye?: number;
    montantDu?: number;
    ligneCaisseId?: number;
    ficheClientId?: number;
    cnssId?: number;
    quittanceMensuelleId?: number;
    declarationAnnuelleId?: number;
    declarationEmployeurAnnuelleId?: number;
    acompteProvisionnelId?: number;
}

export class Paiement implements IPaiement {
    constructor(
        public id?: number,
        public libelle?: string,
        public modePaiement?: ModePaiement,
        public numeroPreuvePaiement?: string,
        public datePaiement?: Moment,
        public montantPaye?: number,
        public montantDu?: number,
        public ligneCaisseId?: number,
        public ficheClientId?: number,
        public cnssId?: number,
        public quittanceMensuelleId?: number,
        public declarationAnnuelleId?: number,
        public declarationEmployeurAnnuelleId?: number,
        public acompteProvisionnelId?: number
    ) {}
}
