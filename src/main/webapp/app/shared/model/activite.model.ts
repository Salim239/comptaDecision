export interface IActivite {
    id?: number;
    code?: string;
    libelle?: string;
    description?: string;
    secteurActiviteId?: number;
    secteurActiviteLibelle?: string;
}

export class Activite implements IActivite {
    constructor(
        public id?: number,
        public code?: string,
        public libelle?: string,
        public description?: string,
        public secteurActiviteId?: number,
        public secteurActiviteLibelle?: string
    ) {}
}
