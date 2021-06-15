import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {IApplicationUserFicheClient} from 'app/shared/model/application-user-fiche-client.model';

type EntityResponseType = HttpResponse<IApplicationUserFicheClient>;
type EntityArrayResponseType = HttpResponse<IApplicationUserFicheClient[]>;

@Injectable({ providedIn: 'root' })
export class ApplicationUserFicheClientService {
    public resourceUrl = SERVER_API_URL + 'api/application-user-fiche-clients';

    constructor(protected http: HttpClient) {}

    create(applicationUserFicheClient: IApplicationUserFicheClient): Observable<EntityResponseType> {
        return this.http.post<IApplicationUserFicheClient>(this.resourceUrl, applicationUserFicheClient, { observe: 'response' });
    }

    update(applicationUserFicheClient: IApplicationUserFicheClient): Observable<EntityResponseType> {
        return this.http.put<IApplicationUserFicheClient>(this.resourceUrl, applicationUserFicheClient, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IApplicationUserFicheClient>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IApplicationUserFicheClient[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
