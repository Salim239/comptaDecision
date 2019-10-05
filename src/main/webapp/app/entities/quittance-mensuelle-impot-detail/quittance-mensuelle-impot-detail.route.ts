import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {Observable, of} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {
    IQuittanceMensuelleImpotDetail,
    QuittanceMensuelleImpotDetail
} from 'app/shared/model/quittance-mensuelle-impot-detail.model';
import {QuittanceMensuelleImpotDetailService} from './quittance-mensuelle-impot-detail.service';
import {QuittanceMensuelleImpotDetailComponent} from './quittance-mensuelle-impot-detail.component';
import {QuittanceMensuelleImpotDetailDetailComponent} from './quittance-mensuelle-impot-detail-detail.component';
import {QuittanceMensuelleImpotDetailUpdateComponent} from './quittance-mensuelle-impot-detail-update.component';
import {QuittanceMensuelleImpotDetailDeletePopupComponent} from './quittance-mensuelle-impot-detail-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class QuittanceMensuelleImpotDetailResolve implements Resolve<IQuittanceMensuelleImpotDetail> {
    constructor(private service: QuittanceMensuelleImpotDetailService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IQuittanceMensuelleImpotDetail> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<QuittanceMensuelleImpotDetail>) => response.ok),
                map((quittanceMensuelleImpotDetail: HttpResponse<QuittanceMensuelleImpotDetail>) => quittanceMensuelleImpotDetail.body)
            );
        }
        return of(new QuittanceMensuelleImpotDetail());
    }
}

export const quittanceMensuelleImpotDetailRoute: Routes = [
    {
        path: '',
        component: QuittanceMensuelleImpotDetailComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpotDetail.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: QuittanceMensuelleImpotDetailDetailComponent,
        resolve: {
            quittanceMensuelleImpotDetail: QuittanceMensuelleImpotDetailResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpotDetail.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: QuittanceMensuelleImpotDetailUpdateComponent,
        resolve: {
            quittanceMensuelleImpotDetail: QuittanceMensuelleImpotDetailResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpotDetail.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: QuittanceMensuelleImpotDetailUpdateComponent,
        resolve: {
            quittanceMensuelleImpotDetail: QuittanceMensuelleImpotDetailResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpotDetail.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const quittanceMensuelleImpotDetailPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: QuittanceMensuelleImpotDetailDeletePopupComponent,
        resolve: {
            quittanceMensuelleImpotDetail: QuittanceMensuelleImpotDetailResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpotDetail.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
