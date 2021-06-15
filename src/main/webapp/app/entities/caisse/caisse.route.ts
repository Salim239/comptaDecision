import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {Observable, of} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {Caisse, ICaisse} from 'app/shared/model/caisse.model';
import {CaisseService} from './caisse.service';
import {CaisseComponent} from './caisse.component';
import {CaisseDetailComponent} from './caisse-detail.component';
import {CaisseUpdateComponent} from './caisse-update.component';
import {CaisseDeletePopupComponent} from './caisse-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class CaisseResolve implements Resolve<ICaisse> {
    constructor(private service: CaisseService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICaisse> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<Caisse>) => response.ok),
                map((caisse: HttpResponse<Caisse>) => caisse.body)
            );
        }
        return of(new Caisse());
    }
}

export const caisseRoute: Routes = [
    {
        path: '',
        component: CaisseComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CaisseDetailComponent,
        resolve: {
            caisse: CaisseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CaisseUpdateComponent,
        resolve: {
            caisse: CaisseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CaisseUpdateComponent,
        resolve: {
            caisse: CaisseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const caissePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CaisseDeletePopupComponent,
        resolve: {
            caisse: CaisseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.caisse.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
