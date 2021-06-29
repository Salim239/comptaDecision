import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IPaiement, Paiement } from 'app/shared/model/paiement.model';
import { PaiementService } from './paiement.service';
import { PaiementComponent } from './paiement.component';
import { PaiementDetailComponent } from './paiement-detail.component';
import { PaiementUpdateComponent } from './paiement-update.component';
import { PaiementDeletePopupComponent } from './paiement-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class PaiementResolve implements Resolve<IPaiement> {
    constructor(private service: PaiementService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPaiement> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Paiement>) => response.ok),
                map((paiement: HttpResponse<Paiement>) => paiement.body)
            );
        }
        return of(new Paiement());
    }
}

export const paiementRoute: Routes = [
    {
        path: '',
        component: PaiementComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.paiement.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: PaiementDetailComponent,
        resolve: {
            paiement: PaiementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.paiement.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: PaiementUpdateComponent,
        resolve: {
            paiement: PaiementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.paiement.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: PaiementUpdateComponent,
        resolve: {
            paiement: PaiementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.paiement.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const paiementPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: PaiementDeletePopupComponent,
        resolve: {
            paiement: PaiementResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.paiement.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
