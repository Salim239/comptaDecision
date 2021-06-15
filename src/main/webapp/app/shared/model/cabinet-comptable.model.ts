import {IFicheClient} from 'app/shared/model/fiche-client.model';

export interface ICabinetComptable {
    id?: number;
    code?: string;
    nombreLicense?: number;
    ficheClientCabinetId?: number;
    clients?: IFicheClient[];
}

export class CabinetComptable implements ICabinetComptable {
    constructor(
        public id?: number,
        public code?: string,
        public nombreLicense?: number,
        public ficheClientCabinetId?: number,
        public clients?: IFicheClient[]
    ) {}
}
