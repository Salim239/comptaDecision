import {ILigneCaisse} from 'app/shared/model/ligne-caisse.model';

export interface ICaisse {
    id?: number;
    montantTotal?: number;
    montantReport?: number;
    cloturee?: boolean;
    ligneCaisses?: ILigneCaisse[];
    ficheClientDesignation?: string;
    ficheClientId?: number;
}

export class Caisse implements ICaisse {
    constructor(
        public id?: number,
        public montantTotal?: number,
        public montantReport?: number,
        public cloturee?: boolean,
        public ligneCaisses?: ILigneCaisse[],
        public ficheClientDesignation?: string,
        public ficheClientId?: number
    ) {
        this.cloturee = this.cloturee || false;
    }
}
