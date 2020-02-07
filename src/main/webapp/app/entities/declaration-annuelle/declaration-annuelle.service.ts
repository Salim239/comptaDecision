import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {DATE_FORMAT} from 'app/shared/constants/input.constants';
import {map} from 'rxjs/operators';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {IDeclarationAnnuelle} from 'app/shared/model/declaration-annuelle.model';

type EntityResponseType = HttpResponse<IDeclarationAnnuelle>;
type EntityArrayResponseType = HttpResponse<IDeclarationAnnuelle[]>;

@Injectable({ providedIn: 'root' })
export class DeclarationAnnuelleService {
    public resourceUrl = SERVER_API_URL + 'api/declaration-annuelles';

    constructor(protected http: HttpClient) {}

    create(declarationAnnuelle: IDeclarationAnnuelle): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(declarationAnnuelle);
        return this.http
            .post<IDeclarationAnnuelle>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(declarationAnnuelle: IDeclarationAnnuelle): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(declarationAnnuelle);
        return this.http
            .put<IDeclarationAnnuelle>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    initEmpty(id, annee, typeDeclaration): Observable<EntityResponseType> {
        return this.http
            .get<IDeclarationAnnuelle>(this.resourceUrl + `/init/${id}/${annee}/${typeDeclaration}`, {observe: 'response'})
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IDeclarationAnnuelle>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IDeclarationAnnuelle[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(declarationAnnuelle: IDeclarationAnnuelle): IDeclarationAnnuelle {
        const copy: IDeclarationAnnuelle = Object.assign({}, declarationAnnuelle, {
            datePaiement:
                declarationAnnuelle.datePaiement != null && declarationAnnuelle.datePaiement.isValid()
                    ? declarationAnnuelle.datePaiement.format(DATE_FORMAT)
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
            res.body.forEach((declarationAnnuelle: IDeclarationAnnuelle) => {
                declarationAnnuelle.datePaiement =
                    declarationAnnuelle.datePaiement != null ? moment(declarationAnnuelle.datePaiement) : null;
            });
        }
        return res;
    }
}
