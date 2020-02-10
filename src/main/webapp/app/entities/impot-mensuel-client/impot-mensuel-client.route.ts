import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ImpotMensuelClient } from 'app/shared/model/impot-mensuel-client.model';
import { ImpotMensuelClientService } from './impot-mensuel-client.service';
import { ImpotMensuelClientComponent } from './impot-mensuel-client.component';
import { ImpotMensuelClientDetailComponent } from './impot-mensuel-client-detail.component';
import { ImpotMensuelClientUpdateComponent } from './impot-mensuel-client-update.component';
import { ImpotMensuelClientDeletePopupComponent } from './impot-mensuel-client-delete-dialog.component';
import { IImpotMensuelClient } from 'app/shared/model/impot-mensuel-client.model';

@Injectable({ providedIn: 'root' })
export class ImpotMensuelClientResolve implements Resolve<IImpotMensuelClient> {
    constructor(private service: ImpotMensuelClientService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IImpotMensuelClient> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ImpotMensuelClient>) => response.ok),
                map((impotMensuelClient: HttpResponse<ImpotMensuelClient>) => impotMensuelClient.body)
            );
        }
        return of(new ImpotMensuelClient());
    }
}

export const impotMensuelClientRoute: Routes = [
    {
        path: '',
        component: ImpotMensuelClientComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.impotMensuelClient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ImpotMensuelClientDetailComponent,
        resolve: {
            impotMensuelClient: ImpotMensuelClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.impotMensuelClient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ImpotMensuelClientUpdateComponent,
        resolve: {
            impotMensuelClient: ImpotMensuelClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.impotMensuelClient.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ImpotMensuelClientUpdateComponent,
        resolve: {
            impotMensuelClient: ImpotMensuelClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.impotMensuelClient.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const impotMensuelClientPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ImpotMensuelClientDeletePopupComponent,
        resolve: {
            impotMensuelClient: ImpotMensuelClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.impotMensuelClient.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
