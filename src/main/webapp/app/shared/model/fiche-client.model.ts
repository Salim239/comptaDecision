import { Moment } from 'moment';
import {IImpotMensuelClient} from "app/shared/model/impot-mensuel-client.model";

export const enum CategorieClient {
    PERSONNE_PHYSIQUE = 'PERSONNE_PHYSIQUE',
    PERSONNE_MORALE = 'PERSONNE_MORALE'
}

export interface IFicheClient {
    id?: number;
    categorieClient?: CategorieClient;
    designation?: string;
    logoContentType?: string;
    logo?: any;
    adresse?: string;
    codePostal?: string;
    telephone?: string;
    fax?: string;
    email?: string;
    matriculeFiscale?: string;
    registreCommerce?: string;
    dateCreation?: Moment;
    cnssEmployeur?: string;
    cnssGerant?: string;
    fichierPatenteContentType?: string;
    fichierPatente?: any;
    regionId?: number;
    regionLibelle?: string;
    villeId?: number;
    villeLibelle?: string;
    secteurActiviteId?: number;
    secteurActiviteLibelle?: string;
    activiteId?: number;
    activiteLibelle?: string;
    activiteScondaireId?: number;
    activiteScondaireLibelle?: string;
    impotMensuelClients?: IImpotMensuelClient[];
    impotMensuelClientsGroupedByMois?: Map<string, IImpotMensuelClient[]>;
}

export class FicheClient implements IFicheClient {
    constructor(
        public id?: number,
        public categorieClient?: CategorieClient,
        public designation?: string,
        public logoContentType?: string,
        public logo?: any,
        public adresse?: string,
        public codePostal?: string,
        public telephone?: string,
        public fax?: string,
        public email?: string,
        public matriculeFiscale?: string,
        public registreCommerce?: string,
        public dateCreation?: Moment,
        public cnssEmployeur?: string,
        public cnssGerant?: string,
        public fichierPatenteContentType?: string,
        public fichierPatente?: any,
        public regionId?: number,
        public regionLibelle?: string,
        public villeId?: number,
        public villeLibelle?: string,
        public secteurActiviteId?: number,
        public secteurActiviteLibelle?: string,
        public activiteId?: number,
        public activiteLibelle?: string,
        public activiteScondaireId?: number,
        public activiteScondaireLibelle?: string,
        public impotMensuelClients?: IImpotMensuelClient[],
        public impotMensuelClientsGroupedByMois?: Map<string, IImpotMensuelClient[]>
    ) {}
}
