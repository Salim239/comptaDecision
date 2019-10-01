import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {UserRouteAccessService} from 'app/core';
import {Observable, of} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {IImpotMensuel, ImpotMensuel} from 'app/shared/model/impot-mensuel.model';
import {ImpotMensuelService} from './impot-mensuel.service';
import {ImpotMensuelComponent} from './impot-mensuel.component';
import {ImpotMensuelDetailComponent} from './impot-mensuel-detail.component';
import {ImpotMensuelUpdateComponent} from './impot-mensuel-update.component';
import {ImpotMensuelDeletePopupComponent} from './impot-mensuel-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class ImpotMensuelResolve implements Resolve<IImpotMensuel> {
    constructor(private service: ImpotMensuelService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IImpotMensuel> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ImpotMensuel>) => response.ok),
                map((impotMensuel: HttpResponse<ImpotMensuel>) => impotMensuel.body)
            );
        }
        return of(new ImpotMensuel());
    }
}

@Injectable({ providedIn: 'root' })
export class parentImpotMensuelsResolve implements Resolve<IImpotMensuel[]> {
    constructor(private service: ImpotMensuelService) {}

    resolve(): Observable<IImpotMensuel[]> {

        return this.service
            .findParentsWithoutChildren()
            .pipe(
                filter((mayBeOk: HttpResponse<IImpotMensuel[]>) => mayBeOk.ok),
                map((response: HttpResponse<IImpotMensuel[]>) => response.body)
            );
    }
}

export const impotMensuelRoute: Routes = [
    {
        path: '',
        component: ImpotMensuelComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.impotMensuel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ImpotMensuelDetailComponent,
        resolve: {
            impotMensuel: ImpotMensuelResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.impotMensuel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ImpotMensuelUpdateComponent,
        resolve: {
            impotMensuel: ImpotMensuelResolve,
            parentImpotMensuels: parentImpotMensuelsResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.impotMensuel.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ImpotMensuelUpdateComponent,
        resolve: {
            impotMensuel: ImpotMensuelResolve,
            parentImpotMensuels: parentImpotMensuelsResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.impotMensuel.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const impotMensuelPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ImpotMensuelDeletePopupComponent,
        resolve: {
            impotMensuel: ImpotMensuelResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.impotMensuel.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
