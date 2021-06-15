export interface IApplicationUserFicheClient {
    id?: number;
    ficheClientDesignation?: string;
    ficheClientId?: number;
    applicationUserId?: number;
}

export class ApplicationUserFicheClient implements IApplicationUserFicheClient {
    constructor(
        public id?: number,
        public ficheClientDesignation?: string,
        public ficheClientId?: number,
        public applicationUserId?: number
    ) {}
}
