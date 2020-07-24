import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Cnss, ICnss } from 'app/shared/model/cnss.model';
import { CnssService } from './cnss.service';
import { CnssComponent } from './cnss.component';
import { CnssDetailComponent } from './cnss-detail.component';
import { CnssUpdateComponent } from './cnss-update.component';
import { CnssDeletePopupComponent } from './cnss-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class CnssResolve implements Resolve<ICnss> {
    constructor(private service: CnssService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICnss> {
        const id = route.params['id'] ? route.params['id'] : null;
        const annee = route.params['annee'] ? route.params['annee'] : null;
        const trimestre = route.params['trimestre'] ? route.params['trimestre'] : null;
        if (id && !annee && !trimestre) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Cnss>) => response.ok),
                map((cnss: HttpResponse<Cnss>) => cnss.body)
            );
        }
        if (id && annee && trimestre) {
            return this.service.initEmpty(id, annee, 'CNSS_EMPLOYEUR', trimestre).pipe(
                filter((response: HttpResponse<Cnss>) => response.ok),
                map((cnss: HttpResponse<Cnss>) => {
                    return cnss.body;
                })
            );
        }
        // return of(new Cnss());
    }
}

export const cnssRoute: Routes = [
    {
        path: '',
        component: CnssComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.cnss.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CnssDetailComponent,
        resolve: {
            cnss: CnssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.cnss.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/:annee/:typeCnss/:trimestre/new',
        component: CnssUpdateComponent,
        resolve: {
            cnss: CnssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.cnss.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CnssUpdateComponent,
        resolve: {
            cnss: CnssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.cnss.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cnssPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CnssDeletePopupComponent,
        resolve: {
            cnss: CnssResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.cnss.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
