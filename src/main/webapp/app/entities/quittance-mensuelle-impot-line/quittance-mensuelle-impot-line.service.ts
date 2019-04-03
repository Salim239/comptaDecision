import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IQuittanceMensuelleImpotLine } from 'app/shared/model/quittance-mensuelle-impot-line.model';

type EntityResponseType = HttpResponse<IQuittanceMensuelleImpotLine>;
type EntityArrayResponseType = HttpResponse<IQuittanceMensuelleImpotLine[]>;

@Injectable({ providedIn: 'root' })
export class QuittanceMensuelleImpotLineService {
    public resourceUrl = SERVER_API_URL + 'api/quittance-mensuelle-impot-lines';

    constructor(protected http: HttpClient) {}

    create(quittanceMensuelleImpotLine: IQuittanceMensuelleImpotLine): Observable<EntityResponseType> {
        return this.http.post<IQuittanceMensuelleImpotLine>(this.resourceUrl, quittanceMensuelleImpotLine, { observe: 'response' });
    }

    update(quittanceMensuelleImpotLine: IQuittanceMensuelleImpotLine): Observable<EntityResponseType> {
        return this.http.put<IQuittanceMensuelleImpotLine>(this.resourceUrl, quittanceMensuelleImpotLine, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IQuittanceMensuelleImpotLine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IQuittanceMensuelleImpotLine[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
