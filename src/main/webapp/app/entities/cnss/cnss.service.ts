import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICnss } from 'app/shared/model/cnss.model';

type EntityResponseType = HttpResponse<ICnss>;
type EntityArrayResponseType = HttpResponse<ICnss[]>;

@Injectable({ providedIn: 'root' })
export class CnssService {
    public resourceUrl = SERVER_API_URL + 'api/cnsses';

    constructor(protected http: HttpClient) {}

    create(cnss: ICnss): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(cnss);
        return this.http
            .post<ICnss>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(cnss: ICnss): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(cnss);
        return this.http
            .put<ICnss>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ICnss>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ICnss[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(cnss: ICnss): ICnss {
        const copy: ICnss = Object.assign({}, cnss, {
            date: cnss.date != null && cnss.date.isValid() ? cnss.date.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.date = res.body.date != null ? moment(res.body.date) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((cnss: ICnss) => {
                cnss.date = cnss.date != null ? moment(cnss.date) : null;
            });
        }
        return res;
    }
}
