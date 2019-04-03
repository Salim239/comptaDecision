import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IImpotMensuel } from 'app/shared/model/impot-mensuel.model';

type EntityResponseType = HttpResponse<IImpotMensuel>;
type EntityArrayResponseType = HttpResponse<IImpotMensuel[]>;

@Injectable({ providedIn: 'root' })
export class ImpotMensuelService {
    public resourceUrl = SERVER_API_URL + 'api/impot-mensuels';

    constructor(protected http: HttpClient) {}

    create(impotMensuel: IImpotMensuel): Observable<EntityResponseType> {
        return this.http.post<IImpotMensuel>(this.resourceUrl, impotMensuel, { observe: 'response' });
    }

    update(impotMensuel: IImpotMensuel): Observable<EntityResponseType> {
        return this.http.put<IImpotMensuel>(this.resourceUrl, impotMensuel, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IImpotMensuel>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IImpotMensuel[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
