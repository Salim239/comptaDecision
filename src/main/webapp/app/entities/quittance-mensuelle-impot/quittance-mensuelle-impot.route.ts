import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { QuittanceMensuelleImpot } from 'app/shared/model/quittance-mensuelle-impot.model';
import { QuittanceMensuelleImpotService } from './quittance-mensuelle-impot.service';
import { QuittanceMensuelleImpotComponent } from './quittance-mensuelle-impot.component';
import { QuittanceMensuelleImpotDetailComponent } from './quittance-mensuelle-impot-detail.component';
import { QuittanceMensuelleImpotUpdateComponent } from './quittance-mensuelle-impot-update.component';
import { QuittanceMensuelleImpotDeletePopupComponent } from './quittance-mensuelle-impot-delete-dialog.component';
import { IQuittanceMensuelleImpot } from 'app/shared/model/quittance-mensuelle-impot.model';

@Injectable({ providedIn: 'root' })
export class QuittanceMensuelleImpotResolve implements Resolve<IQuittanceMensuelleImpot> {
    constructor(private service: QuittanceMensuelleImpotService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IQuittanceMensuelleImpot> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<QuittanceMensuelleImpot>) => response.ok),
                map((quittanceMensuelleImpot: HttpResponse<QuittanceMensuelleImpot>) => quittanceMensuelleImpot.body)
            );
        }
        return of(new QuittanceMensuelleImpot());
    }
}

export const quittanceMensuelleImpotRoute: Routes = [
    {
        path: '',
        component: QuittanceMensuelleImpotComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpot.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: QuittanceMensuelleImpotDetailComponent,
        resolve: {
            quittanceMensuelleImpot: QuittanceMensuelleImpotResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpot.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: QuittanceMensuelleImpotUpdateComponent,
        resolve: {
            quittanceMensuelleImpot: QuittanceMensuelleImpotResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpot.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: QuittanceMensuelleImpotUpdateComponent,
        resolve: {
            quittanceMensuelleImpot: QuittanceMensuelleImpotResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpot.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const quittanceMensuelleImpotPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: QuittanceMensuelleImpotDeletePopupComponent,
        resolve: {
            quittanceMensuelleImpot: QuittanceMensuelleImpotResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpot.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
