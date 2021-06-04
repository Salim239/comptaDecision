import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IQuittanceMensuelleLigne } from 'app/shared/model/quittance-mensuelle-ligne.model';

type EntityResponseType = HttpResponse<IQuittanceMensuelleLigne>;
type EntityArrayResponseType = HttpResponse<IQuittanceMensuelleLigne[]>;

@Injectable({ providedIn: 'root' })
export class QuittanceMensuelleLigneService {
    public resourceUrl = SERVER_API_URL + 'api/quittance-mensuelle-lignes';

    constructor(protected http: HttpClient) {}

    create(quittanceMensuelleLigne: IQuittanceMensuelleLigne): Observable<EntityResponseType> {
        return this.http.post<IQuittanceMensuelleLigne>(this.resourceUrl, quittanceMensuelleLigne, { observe: 'response' });
    }

    update(quittanceMensuelleLigne: IQuittanceMensuelleLigne): Observable<EntityResponseType> {
        return this.http.put<IQuittanceMensuelleLigne>(this.resourceUrl, quittanceMensuelleLigne, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IQuittanceMensuelleLigne>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IQuittanceMensuelleLigne[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
