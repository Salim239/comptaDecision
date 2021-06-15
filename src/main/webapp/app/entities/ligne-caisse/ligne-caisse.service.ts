import {Injectable} from '@angular/core';
import {HttpClient, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared';
import {ILigneCaisse} from 'app/shared/model/ligne-caisse.model';

type EntityResponseType = HttpResponse<ILigneCaisse>;
type EntityArrayResponseType = HttpResponse<ILigneCaisse[]>;

@Injectable({ providedIn: 'root' })
export class LigneCaisseService {
    public resourceUrl = SERVER_API_URL + 'api/ligne-caisses';

    constructor(protected http: HttpClient) {}

    create(ligneCaisse: ILigneCaisse): Observable<EntityResponseType> {
        return this.http.post<ILigneCaisse>(this.resourceUrl, ligneCaisse, { observe: 'response' });
    }

    update(ligneCaisse: ILigneCaisse): Observable<EntityResponseType> {
        return this.http.put<ILigneCaisse>(this.resourceUrl, ligneCaisse, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ILigneCaisse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ILigneCaisse[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
