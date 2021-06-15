import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {ICabinetComptable} from 'app/shared/model/cabinet-comptable.model';

type EntityResponseType = HttpResponse<ICabinetComptable>;
type EntityArrayResponseType = HttpResponse<ICabinetComptable[]>;

@Injectable({ providedIn: 'root' })
export class CabinetComptableService {
    public resourceUrl = SERVER_API_URL + 'api/cabinet-comptables';

    constructor(protected http: HttpClient) {}

    create(cabinetComptable: ICabinetComptable): Observable<EntityResponseType> {
        return this.http.post<ICabinetComptable>(this.resourceUrl, cabinetComptable, { observe: 'response' });
    }

    update(cabinetComptable: ICabinetComptable): Observable<EntityResponseType> {
        return this.http.put<ICabinetComptable>(this.resourceUrl, cabinetComptable, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ICabinetComptable>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICabinetComptable[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
