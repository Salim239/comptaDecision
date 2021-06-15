import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {Observable, of} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {
    ApplicationUserFicheClient,
    IApplicationUserFicheClient
} from 'app/shared/model/application-user-fiche-client.model';
import {ApplicationUserFicheClientService} from './application-user-fiche-client.service';
import {ApplicationUserFicheClientComponent} from './application-user-fiche-client.component';
import {ApplicationUserFicheClientDetailComponent} from './application-user-fiche-client-detail.component';
import {ApplicationUserFicheClientUpdateComponent} from './application-user-fiche-client-update.component';
import {ApplicationUserFicheClientDeletePopupComponent} from './application-user-fiche-client-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class ApplicationUserFicheClientResolve implements Resolve<IApplicationUserFicheClient> {
    constructor(private service: ApplicationUserFicheClientService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IApplicationUserFicheClient> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ApplicationUserFicheClient>) => response.ok),
                map((applicationUserFicheClient: HttpResponse<ApplicationUserFicheClient>) => applicationUserFicheClient.body)
            );
        }
        return of(new ApplicationUserFicheClient());
    }
}

export const applicationUserFicheClientRoute: Routes = [
    {
        path: '',
        component: ApplicationUserFicheClientComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.applicationUserFicheClient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ApplicationUserFicheClientDetailComponent,
        resolve: {
            applicationUserFicheClient: ApplicationUserFicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.applicationUserFicheClient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ApplicationUserFicheClientUpdateComponent,
        resolve: {
            applicationUserFicheClient: ApplicationUserFicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.applicationUserFicheClient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ApplicationUserFicheClientUpdateComponent,
        resolve: {
            applicationUserFicheClient: ApplicationUserFicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.applicationUserFicheClient.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const applicationUserFicheClientPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ApplicationUserFicheClientDeletePopupComponent,
        resolve: {
            applicationUserFicheClient: ApplicationUserFicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.applicationUserFicheClient.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
