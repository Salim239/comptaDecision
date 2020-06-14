import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICategorieCnssGerant } from 'app/shared/model/categorie-cnss-gerant.model';
import { IActivite } from 'app/shared/model/activite.model';

type EntityResponseType = HttpResponse<ICategorieCnssGerant>;
type EntityArrayResponseType = HttpResponse<ICategorieCnssGerant[]>;

@Injectable({ providedIn: 'root' })
export class CategorieCnssGerantService {
    public resourceUrl = SERVER_API_URL + 'api/categorie-cnss-gerants';

    constructor(protected http: HttpClient) {}

    create(categorieCnssGerant: ICategorieCnssGerant): Observable<EntityResponseType> {
        return this.http.post<ICategorieCnssGerant>(this.resourceUrl, categorieCnssGerant, { observe: 'response' });
    }

    update(categorieCnssGerant: ICategorieCnssGerant): Observable<EntityResponseType> {
        return this.http.put<ICategorieCnssGerant>(this.resourceUrl, categorieCnssGerant, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICategorieCnssGerant>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    findByActiviteId(activiteId: number): Observable<EntityArrayResponseType> {
        return this.http.get<IActivite[]>(`${this.resourceUrl}/activite/${activiteId}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICategorieCnssGerant[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
