import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IImpotAnnuel, ImpotAnnuel } from 'app/shared/model/impot-annuel.model';
import { ImpotAnnuelService } from './impot-annuel.service';
import { ImpotAnnuelComponent } from './impot-annuel.component';
import { ImpotAnnuelLigneComponent } from './impot-annuel-detail.component';
import { ImpotAnnuelUpdateComponent } from './impot-annuel-update.component';
import { ImpotAnnuelDeletePopupComponent } from './impot-annuel-delete-dialog.component';
import { IImpotMensuelLigne } from 'app/shared/model/impot-mensuel-ligne.model';
import { ImpotMensuelLigneService } from 'app/entities/impot-mensuel/impot-mensuel-line/impot-detail.service';

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
export class ImpotAnnuelAllResolve implements Resolve<IImpotAnnuel[]> {
    constructor(private service: ImpotAnnuelService) {}

    resolve(): Observable<IImpotAnnuel[]> {
        return this.service.query().pipe(
            filter((mayBeOk: HttpResponse<IImpotAnnuel[]>) => mayBeOk.ok),
            map((response: HttpResponse<IImpotAnnuel[]>) => response.body)
        );
    }
}

@Injectable({ providedIn: 'root' })
export class ImpotMensuelLigneResolve implements Resolve<IImpotMensuelLigne[]> {
    constructor(private service: ImpotMensuelLigneService) {}

    resolve(): Observable<IImpotMensuelLigne[]> {
        return this.service.findAll().pipe(
            filter((mayBeOk: HttpResponse<IImpotMensuelLigne[]>) => mayBeOk.ok),
            map((response: HttpResponse<IImpotMensuelLigne[]>) => response.body)
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
        component: ImpotAnnuelLigneComponent,
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
            impotMensuelLignes: ImpotMensuelLigneResolve,
            impotAnnuelEnfants: ImpotAnnuelAllResolve
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
            impotMensuelLignes: ImpotMensuelLigneResolve,
            impotAnnuelEnfants: ImpotAnnuelAllResolve
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
