import { Moment } from 'moment';
import { IImpotMensuelClient } from 'app/shared/model/impot-mensuel-client.model';
import { CentreAdministratif } from 'app/shared/model/centre-administratif.model';

export const enum CategorieClient {
    PERSONNE_PHYSIQUE = 'PERSONNE_PHYSIQUE',
    PERSONNE_MORALE = 'PERSONNE_MORALE'
}

export const enum CodeTVA {
    A = 'A',
    N = 'N',
    B = 'B',
    P = 'P'
}

export const enum CategorieActivite {
    M = 'M',
    P = 'P',
    C = 'C'
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
    codeTva?: CodeTVA;
    numeroEtablissementSecondaire?: string;
    categorieActivite?: CategorieActivite;
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
    secteurActivite1Id?: number;
    secteurActivite1Libelle?: string;
    activite1Id?: number;
    activite1Libelle?: string;
    secteurActivite2Id?: number;
    secteurActivite2Libelle?: string;
    activite2Id?: number;
    activite2Libelle?: string;
    secteurActivite3Id?: number;
    secteurActivite3Libelle?: string;
    activite3Id?: number;
    activite3Libelle?: string;
    tauxCnssAccident?: number;
    categorieCnssGerantId?: number;
    categorieCnssGerantLibelle?: string;
    administrationCnss?: CentreAdministratif;
    administrationFiscale?: CentreAdministratif;
    administrationImpot?: CentreAdministratif;
    impotMensuelClients?: IImpotMensuelClient[];
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
        public codeTva?: CodeTVA,
        public numeroEtablissementSecondaire?: string,
        public categorieActivite?: CategorieActivite,
        public registreCommerce?: string,
        public dateCreation?: Moment,
        public cnssEmployeur?: string,
        public cnssGerant?: string,
        public categorieCnssGerantId?: number,
        public categorieCnssGerantLibelle?: string,
        public fichierPatenteContentType?: string,
        public fichierPatente?: any,
        public regionId?: number,
        public regionLibelle?: string,
        public villeId?: number,
        public villeLibelle?: string,
        public secteurActivite1Id?: number,
        public secteurActivite1Libelle?: string,
        public activite1Id?: number,
        public activite1Libelle?: string,
        public secteurActivite2Id?: number,
        public secteurActivite2Libelle?: string,
        public activite2Id?: number,
        public activite2Libelle?: string,
        public secteurActivite3Id?: number,
        public secteurActivite3Libelle?: string,
        public activite3Id?: number,
        public activite3Libelle?: string,
        public tauxCnssAccident?: number,
        public administrationCnss?: CentreAdministratif,
        public administrationFiscale?: CentreAdministratif,
        public administrationImpot?: CentreAdministratif,
        public impotMensuelClients?: IImpotMensuelClient[]
    ) {}
}
