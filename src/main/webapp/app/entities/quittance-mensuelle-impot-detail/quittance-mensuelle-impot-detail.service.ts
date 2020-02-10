import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {IQuittanceMensuelleImpotDetail} from 'app/shared/model/quittance-mensuelle-impot-detail.model';

type EntityResponseType = HttpResponse<IQuittanceMensuelleImpotDetail>;
type EntityArrayResponseType = HttpResponse<IQuittanceMensuelleImpotDetail[]>;

@Injectable({ providedIn: 'root' })
export class QuittanceMensuelleImpotDetailService {
    public resourceUrl = SERVER_API_URL + 'api/quittance-mensuelle-impot-details';

    constructor(protected http: HttpClient) {}

    create(quittanceMensuelleImpotDetail: IQuittanceMensuelleImpotDetail): Observable<EntityResponseType> {
        return this.http.post<IQuittanceMensuelleImpotDetail>(this.resourceUrl, quittanceMensuelleImpotDetail, { observe: 'response' });
    }

    update(quittanceMensuelleImpotDetail: IQuittanceMensuelleImpotDetail): Observable<EntityResponseType> {
        return this.http.put<IQuittanceMensuelleImpotDetail>(this.resourceUrl, quittanceMensuelleImpotDetail, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IQuittanceMensuelleImpotDetail>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IQuittanceMensuelleImpotDetail[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
