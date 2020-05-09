import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { IImpotMensuel } from 'app/shared/model/impot-mensuel.model';

type EntityResponseType = HttpResponse<IImpotMensuel>;
type EntityArrayResponseType = HttpResponse<IImpotMensuel[]>;

@Injectable({ providedIn: 'root' })
export class ImpotMensuelDetailService {
    public resourceUrl = SERVER_API_URL + 'api/impot-mensuel-details';

    constructor(protected http: HttpClient) {}

    findAll(): Observable<EntityArrayResponseType> {
        return this.http.get<IImpotMensuel[]>(this.resourceUrl, { observe: 'response' });
    }
}
