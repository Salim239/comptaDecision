import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISecteurActivite } from 'app/shared/model/secteur-activite.model';
import { IActivite } from 'app/shared/model/activite.model';

type EntityResponseType = HttpResponse<ISecteurActivite>;
type EntityArrayResponseType = HttpResponse<ISecteurActivite[]>;

@Injectable({ providedIn: 'root' })
export class SecteurActiviteService {
    public resourceUrl = SERVER_API_URL + 'api/secteur-activites';

    constructor(protected http: HttpClient) {}

    create(secteurActivite: ISecteurActivite): Observable<EntityResponseType> {
        return this.http.post<ISecteurActivite>(this.resourceUrl, secteurActivite, { observe: 'response' });
    }

    update(secteurActivite: ISecteurActivite): Observable<EntityResponseType> {
        return this.http.put<ISecteurActivite>(this.resourceUrl, secteurActivite, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ISecteurActivite>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    findByActiviteId(activiteId: number): Observable<EntityArrayResponseType> {
        return this.http.get<IActivite[]>(`${this.resourceUrl}/activite/${activiteId}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ISecteurActivite[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
