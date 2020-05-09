import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IImpotAnnuel, ImpotAnnuel } from 'app/shared/model/impot-annuel.model';
import { ImpotAnnuelService } from './impot-annuel.service';
import { ImpotAnnuelComponent } from './impot-annuel.component';
import { ImpotAnnuelDetailComponent } from './impot-annuel-detail.component';
import { ImpotAnnuelUpdateComponent } from './impot-annuel-update.component';
import { ImpotAnnuelDeletePopupComponent } from './impot-annuel-delete-dialog.component';
import { IImpotMensuelDetail } from 'app/shared/model/impot-mensuel-detail.model';
import { ImpotMensuelDetailService } from 'app/entities/impot-mensuel/impot-mensuel-line/impot-detail.service';

@Injectable({ providedIn: 'root' })
export class ImpotAnnuelResolve implements Resolve<IImpotAnnuel> {
    constructor(private service: ImpotAnnuelService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IImpotAnnuel> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ImpotAnnuel>) => response.ok),
                map((impotAnnuel: HttpResponse<ImpotAnnuel>) => impotAnnuel.body)
            );
        }
        return of(new ImpotAnnuel());
    }
}

@Injectable({ providedIn: 'root' })
export class ImpotMensuelDetailResolve implements Resolve<IImpotMensuelDetail[]> {
    constructor(private service: ImpotMensuelDetailService) {}

    resolve(): Observable<IImpotMensuelDetail[]> {
        return this.service.findAll().pipe(
            filter((mayBeOk: HttpResponse<IImpotMensuelDetail[]>) => mayBeOk.ok),
            map((response: HttpResponse<IImpotMensuelDetail[]>) => response.body)
        );
    }
}

export const impotAnnuelRoute: Routes = [
    {
        path: '',
        component: ImpotAnnuelComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.impotAnnuel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ImpotAnnuelDetailComponent,
        resolve: {
            impotAnnuel: ImpotAnnuelResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.impotAnnuel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ImpotAnnuelUpdateComponent,
        resolve: {
            impotAnnuel: ImpotAnnuelResolve,
            impotMensuelDetails: ImpotMensuelDetailResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.impotAnnuel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ImpotAnnuelUpdateComponent,
        resolve: {
            impotAnnuel: ImpotAnnuelResolve,
            impotMensuelDetails: ImpotMensuelDetailResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.impotAnnuel.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const impotAnnuelPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ImpotAnnuelDeletePopupComponent,
        resolve: {
            impotAnnuel: ImpotAnnuelResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.impotAnnuel.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
