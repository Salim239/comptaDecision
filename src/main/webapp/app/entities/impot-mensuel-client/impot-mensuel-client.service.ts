import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IImpotMensuelClient } from 'app/shared/model/impot-mensuel-client.model';

type EntityResponseType = HttpResponse<IImpotMensuelClient>;
type EntityArrayResponseType = HttpResponse<IImpotMensuelClient[]>;

@Injectable({ providedIn: 'root' })
export class ImpotMensuelClientService {
    public resourceUrl = SERVER_API_URL + 'api/impot-mensuel-clients';

    constructor(protected http: HttpClient) {}

    create(impotMensuelClient: IImpotMensuelClient): Observable<EntityResponseType> {
        return this.http.post<IImpotMensuelClient>(this.resourceUrl, impotMensuelClient, { observe: 'response' });
    }

    update(impotMensuelClient: IImpotMensuelClient): Observable<EntityResponseType> {
        return this.http.put<IImpotMensuelClient>(this.resourceUrl, impotMensuelClient, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IImpotMensuelClient>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IImpotMensuelClient[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
