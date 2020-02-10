import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {DATE_FORMAT} from 'app/shared/constants/input.constants';
import {map} from 'rxjs/operators';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {IFicheClient} from 'app/shared/model/fiche-client.model';

type EntityResponseType = HttpResponse<IFicheClient>;
type EntityArrayResponseType = HttpResponse<IFicheClient[]>;

@Injectable({ providedIn: 'root' })
export class FicheClientService {
    public resourceUrl = SERVER_API_URL + 'api/fiche-clients';

    constructor(protected http: HttpClient) {}

    initEmpty(): Observable<EntityResponseType> {
        return this.http
            .get<IFicheClient>(this.resourceUrl + '/init', { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    create(ficheClient: IFicheClient): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(ficheClient);
        return this.http
            .post<IFicheClient>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(ficheClient: IFicheClient): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(ficheClient);
        return this.http
            .put<IFicheClient>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IFicheClient>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IFicheClient[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(ficheClient: IFicheClient): IFicheClient {
        const copy: IFicheClient = Object.assign({}, ficheClient, {
            dateCreation:
                ficheClient.dateCreation != null && ficheClient.dateCreation.isValid() ? ficheClient.dateCreation.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateCreation = res.body.dateCreation != null ? moment(res.body.dateCreation) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((ficheClient: IFicheClient) => {
                ficheClient.dateCreation = ficheClient.dateCreation != null ? moment(ficheClient.dateCreation) : null;
            });
        }
        return res;
    }
}
