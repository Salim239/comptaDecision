import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {DATE_FORMAT} from 'app/shared/constants/input.constants';
import {map} from 'rxjs/operators';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {IQuittanceMensuelleImpot} from 'app/shared/model/quittance-mensuelle-impot.model';

type EntityResponseType = HttpResponse<IQuittanceMensuelleImpot>;
type EntityArrayResponseType = HttpResponse<IQuittanceMensuelleImpot[]>;

@Injectable({ providedIn: 'root' })
export class QuittanceMensuelleImpotService {
    public resourceUrl = SERVER_API_URL + 'api/quittance-mensuelle-impots';

    constructor(protected http: HttpClient) {}

    initByParams(ficheClientId: number): Observable<EntityResponseType> {
        return this.http
            .get<IQuittanceMensuelleImpot>(`${this.resourceUrl}/ficheClient/${ficheClientId}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    create(quittanceMensuelleImpot: IQuittanceMensuelleImpot): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(quittanceMensuelleImpot);
        return this.http
            .post<IQuittanceMensuelleImpot>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(quittanceMensuelleImpot: IQuittanceMensuelleImpot): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(quittanceMensuelleImpot);
        return this.http
            .put<IQuittanceMensuelleImpot>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

        find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IQuittanceMensuelleImpot>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IQuittanceMensuelleImpot[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(quittanceMensuelleImpot: IQuittanceMensuelleImpot): IQuittanceMensuelleImpot {
        const copy: IQuittanceMensuelleImpot = Object.assign({}, quittanceMensuelleImpot, {
            datePaiement:
                quittanceMensuelleImpot.datePaiement != null && quittanceMensuelleImpot.datePaiement.isValid()
                    ? quittanceMensuelleImpot.datePaiement.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.datePaiement = res.body.datePaiement != null ? moment(res.body.datePaiement) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((quittanceMensuelleImpot: IQuittanceMensuelleImpot) => {
                quittanceMensuelleImpot.datePaiement =
                    quittanceMensuelleImpot.datePaiement != null ? moment(quittanceMensuelleImpot.datePaiement) : null;
            });
        }
        return res;
    }
}
