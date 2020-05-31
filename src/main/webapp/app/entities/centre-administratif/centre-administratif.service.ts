import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { CentreAdministratif, ICentreAdministratif, TypeCentreAdministratif } from 'app/shared/model/centre-administratif.model';

type EntityResponseType = HttpResponse<ICentreAdministratif>;
type EntityArrayResponseType = HttpResponse<ICentreAdministratif[]>;

@Injectable({ providedIn: 'root' })
export class CentreAdministratifService {
    public resourceUrl = SERVER_API_URL + 'api/centre-administratifs';

    constructor(protected http: HttpClient) {}

    create(centreAdministratif: ICentreAdministratif): Observable<EntityResponseType> {
        return this.http.post<ICentreAdministratif>(this.resourceUrl, centreAdministratif, { observe: 'response' });
    }

    update(centreAdministratif: ICentreAdministratif): Observable<EntityResponseType> {
        return this.http.put<ICentreAdministratif>(this.resourceUrl, centreAdministratif, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICentreAdministratif>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICentreAdministratif[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    findByType(type: TypeCentreAdministratif): Observable<EntityArrayResponseType> {
        return this.http.get<ICentreAdministratif[]>(`${this.resourceUrl}/${type}`, { observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
