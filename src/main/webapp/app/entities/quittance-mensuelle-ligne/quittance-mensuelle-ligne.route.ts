import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IQuittanceMensuelleLigne, QuittanceMensuelleLigne } from 'app/shared/model/quittance-mensuelle-ligne.model';
import { QuittanceMensuelleLigneService } from './quittance-mensuelle-ligne.service';
import { QuittanceMensuelleLigneComponent } from './quittance-mensuelle-ligne.component';
import { QuittanceMensuelleLigneLigneComponent } from './quittance-mensuelle-ligne-detail.component';
import { QuittanceMensuelleLigneUpdateComponent } from './quittance-mensuelle-ligne-update.component';
import { QuittanceMensuelleLigneDeletePopupComponent } from './quittance-mensuelle-ligne-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class QuittanceMensuelleLigneResolve implements Resolve<IQuittanceMensuelleLigne> {
    constructor(private service: QuittanceMensuelleLigneService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IQuittanceMensuelleLigne> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<QuittanceMensuelleLigne>) => response.ok),
                map((quittanceMensuelleLigne: HttpResponse<QuittanceMensuelleLigne>) => quittanceMensuelleLigne.body)
            );
        }
        return of(new QuittanceMensuelleLigne());
    }
}

export const quittanceMensuelleLigneRoute: Routes = [
    {
        path: '',
        component: QuittanceMensuelleLigneComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.quittanceMensuelleLigne.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: QuittanceMensuelleLigneLigneComponent,
        resolve: {
            quittanceMensuelleLigne: QuittanceMensuelleLigneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleLigne.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: QuittanceMensuelleLigneUpdateComponent,
        resolve: {
            quittanceMensuelleLigne: QuittanceMensuelleLigneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleLigne.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: QuittanceMensuelleLigneUpdateComponent,
        resolve: {
            quittanceMensuelleLigne: QuittanceMensuelleLigneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleLigne.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const quittanceMensuelleLignePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: QuittanceMensuelleLigneDeletePopupComponent,
        resolve: {
            quittanceMensuelleLigne: QuittanceMensuelleLigneResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleLigne.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
