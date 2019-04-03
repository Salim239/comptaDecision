import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAcompteProvisionnel } from 'app/shared/model/acompte-provisionnel.model';

type EntityResponseType = HttpResponse<IAcompteProvisionnel>;
type EntityArrayResponseType = HttpResponse<IAcompteProvisionnel[]>;

@Injectable({ providedIn: 'root' })
export class AcompteProvisionnelService {
    public resourceUrl = SERVER_API_URL + 'api/acompte-provisionnels';

    constructor(protected http: HttpClient) {}

    create(acompteProvisionnel: IAcompteProvisionnel): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(acompteProvisionnel);
        return this.http
            .post<IAcompteProvisionnel>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(acompteProvisionnel: IAcompteProvisionnel): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(acompteProvisionnel);
        return this.http
            .put<IAcompteProvisionnel>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IAcompteProvisionnel>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IAcompteProvisionnel[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(acompteProvisionnel: IAcompteProvisionnel): IAcompteProvisionnel {
        const copy: IAcompteProvisionnel = Object.assign({}, acompteProvisionnel, {
            date:
                acompteProvisionnel.date != null && acompteProvisionnel.date.isValid() ? acompteProvisionnel.date.format(DATE_FORMAT) : null
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
            res.body.forEach((acompteProvisionnel: IAcompteProvisionnel) => {
                acompteProvisionnel.date = acompteProvisionnel.date != null ? moment(acompteProvisionnel.date) : null;
            });
        }
        return res;
    }
}
