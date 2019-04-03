import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { AcompteProvisionnel } from 'app/shared/model/acompte-provisionnel.model';
import { AcompteProvisionnelService } from './acompte-provisionnel.service';
import { AcompteProvisionnelComponent } from './acompte-provisionnel.component';
import { AcompteProvisionnelDetailComponent } from './acompte-provisionnel-detail.component';
import { AcompteProvisionnelUpdateComponent } from './acompte-provisionnel-update.component';
import { AcompteProvisionnelDeletePopupComponent } from './acompte-provisionnel-delete-dialog.component';
import { IAcompteProvisionnel } from 'app/shared/model/acompte-provisionnel.model';

@Injectable({ providedIn: 'root' })
export class AcompteProvisionnelResolve implements Resolve<IAcompteProvisionnel> {
    constructor(private service: AcompteProvisionnelService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IAcompteProvisionnel> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<AcompteProvisionnel>) => response.ok),
                map((acompteProvisionnel: HttpResponse<AcompteProvisionnel>) => acompteProvisionnel.body)
            );
        }
        return of(new AcompteProvisionnel());
    }
}

export const acompteProvisionnelRoute: Routes = [
    {
        path: '',
        component: AcompteProvisionnelComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.acompteProvisionnel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: AcompteProvisionnelDetailComponent,
        resolve: {
            acompteProvisionnel: AcompteProvisionnelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.acompteProvisionnel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: AcompteProvisionnelUpdateComponent,
        resolve: {
            acompteProvisionnel: AcompteProvisionnelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.acompteProvisionnel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: AcompteProvisionnelUpdateComponent,
        resolve: {
            acompteProvisionnel: AcompteProvisionnelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.acompteProvisionnel.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const acompteProvisionnelPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: AcompteProvisionnelDeletePopupComponent,
        resolve: {
            acompteProvisionnel: AcompteProvisionnelResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.acompteProvisionnel.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
