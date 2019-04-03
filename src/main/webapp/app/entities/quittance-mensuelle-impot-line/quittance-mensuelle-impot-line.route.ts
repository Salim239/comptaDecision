import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { QuittanceMensuelleImpotLine } from 'app/shared/model/quittance-mensuelle-impot-line.model';
import { QuittanceMensuelleImpotLineService } from './quittance-mensuelle-impot-line.service';
import { QuittanceMensuelleImpotLineComponent } from './quittance-mensuelle-impot-line.component';
import { QuittanceMensuelleImpotLineDetailComponent } from './quittance-mensuelle-impot-line-detail.component';
import { QuittanceMensuelleImpotLineUpdateComponent } from './quittance-mensuelle-impot-line-update.component';
import { QuittanceMensuelleImpotLineDeletePopupComponent } from './quittance-mensuelle-impot-line-delete-dialog.component';
import { IQuittanceMensuelleImpotLine } from 'app/shared/model/quittance-mensuelle-impot-line.model';

@Injectable({ providedIn: 'root' })
export class QuittanceMensuelleImpotLineResolve implements Resolve<IQuittanceMensuelleImpotLine> {
    constructor(private service: QuittanceMensuelleImpotLineService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IQuittanceMensuelleImpotLine> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<QuittanceMensuelleImpotLine>) => response.ok),
                map((quittanceMensuelleImpotLine: HttpResponse<QuittanceMensuelleImpotLine>) => quittanceMensuelleImpotLine.body)
            );
        }
        return of(new QuittanceMensuelleImpotLine());
    }
}

export const quittanceMensuelleImpotLineRoute: Routes = [
    {
        path: '',
        component: QuittanceMensuelleImpotLineComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpotLine.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: QuittanceMensuelleImpotLineDetailComponent,
        resolve: {
            quittanceMensuelleImpotLine: QuittanceMensuelleImpotLineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpotLine.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: QuittanceMensuelleImpotLineUpdateComponent,
        resolve: {
            quittanceMensuelleImpotLine: QuittanceMensuelleImpotLineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpotLine.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: QuittanceMensuelleImpotLineUpdateComponent,
        resolve: {
            quittanceMensuelleImpotLine: QuittanceMensuelleImpotLineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpotLine.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const quittanceMensuelleImpotLinePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: QuittanceMensuelleImpotLineDeletePopupComponent,
        resolve: {
            quittanceMensuelleImpotLine: QuittanceMensuelleImpotLineResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpotLine.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
