import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {IDeclarationEmployeurAnnuelle} from 'app/shared/model/declaration-employeur-annuelle.model';
import {ICnss} from 'app/shared/model/cnss.model';

type EntityResponseType = HttpResponse<IDeclarationEmployeurAnnuelle>;
type EntityArrayResponseType = HttpResponse<IDeclarationEmployeurAnnuelle[]>;

@Injectable({ providedIn: 'root' })
export class DeclarationEmployeurAnnuelleService {
    public resourceUrl = SERVER_API_URL + 'api/declaration-employeur-annuelles';

    constructor(protected http: HttpClient) {}

    initEmpty(id, annee): Observable<EntityResponseType> {
        return this.http
            .get<ICnss>(this.resourceUrl + `/init/${id}/${annee}`, {observe: 'response'});
    }

    create(declarationEmployeurAnnuelle: IDeclarationEmployeurAnnuelle): Observable<EntityResponseType> {
        return this.http.post<IDeclarationEmployeurAnnuelle>(this.resourceUrl, declarationEmployeurAnnuelle, { observe: 'response' });
    }

    update(declarationEmployeurAnnuelle: IDeclarationEmployeurAnnuelle): Observable<EntityResponseType> {
        return this.http.put<IDeclarationEmployeurAnnuelle>(this.resourceUrl, declarationEmployeurAnnuelle, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IDeclarationEmployeurAnnuelle>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDeclarationEmployeurAnnuelle[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
