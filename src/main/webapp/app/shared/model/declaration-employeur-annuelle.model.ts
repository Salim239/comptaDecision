import {Moment} from 'moment';

export interface IDeclarationEmployeurAnnuelle {
    id?: number;
    annee?: number;
    montantAnnexe1?: number;
    montantAnnexe2?: number;
    montantAnnexe3?: number;
    montantAnnexe4?: number;
    montantAnnexe5?: number;
    montantAnnexe6?: number;
    montantAnnexe7?: number;
    montantAnnexe8?: number;
    montantAnnexe9?: number;
    montantAnnexe10?: number;
    montantAnnexe11?: number;
    montantAnnexe12?: number;
    ficheClientId?: number;
    ficheClientDesignation?: string;
    ficheClientMatriculeFiscale?: string;
    ficheClientRegistreCommerce?: string;
    ficheClientDateCreation?: Moment;
}

export class DeclarationEmployeurAnnuelle implements IDeclarationEmployeurAnnuelle {
    constructor(
        public id?: number,
        public annee?: number,
        public montantAnnexe1?: number,
        public montantAnnexe2?: number,
        public montantAnnexe3?: number,
        public montantAnnexe4?: number,
        public montantAnnexe5?: number,
        public montantAnnexe6?: number,
        public montantAnnexe7?: number,
        public montantAnnexe8?: number,
        public montantAnnexe9?: number,
        public montantAnnexe10?: number,
        public montantAnnexe11?: number,
        public montantAnnexe12?: number,
        public ficheClientId?: number,
        public ficheClientDesignation?: string,
    public ficheClientMatriculeFiscale?: string,
    public ficheClientRegistreCommerce?: string,
    public ficheClientDateCreation?: Moment
    ) {}
}
