import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {FicheClient, IFicheClient} from 'app/shared/model/fiche-client.model';
import {FicheClientService} from './fiche-client.service';
import {FicheClientComponent} from './fiche-client.component';
import {FicheClientDetailComponent} from './fiche-client-detail.component';
import {FicheClientUpdateComponent} from './fiche-client-update.component';
import {FicheClientDeletePopupComponent} from './fiche-client-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class FicheClientResolve implements Resolve<IFicheClient> {
    constructor(private service: FicheClientService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IFicheClient> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<FicheClient>) => response.ok), map((ficheClient: HttpResponse<FicheClient>) => {
                const data = ficheClient.body;
                return data;
            })
        );
        }
        return this.service.initEmpty()
            .pipe(
                filter((response: HttpResponse<FicheClient>) => response.ok),
                map((ficheClient: HttpResponse<FicheClient>) => {
                    const data = ficheClient.body;
                    return data;
                })
            );
        // return of(new FicheClient());
    }
}

export const ficheClientRoute: Routes = [
    {
        path: '',
        component: FicheClientComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.ficheClient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: FicheClientDetailComponent,
        resolve: {
            ficheClient: FicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.ficheClient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: FicheClientUpdateComponent,
        resolve: {
            ficheClient: FicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.ficheClient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: FicheClientUpdateComponent,
        resolve: {
            ficheClient: FicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.ficheClient.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ficheClientPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: FicheClientDeletePopupComponent,
        resolve: {
            ficheClient: FicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.ficheClient.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
