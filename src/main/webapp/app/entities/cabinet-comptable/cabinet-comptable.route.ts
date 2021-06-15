import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {Observable, of} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {CabinetComptable, ICabinetComptable} from 'app/shared/model/cabinet-comptable.model';
import {CabinetComptableService} from './cabinet-comptable.service';
import {CabinetComptableComponent} from './cabinet-comptable.component';
import {CabinetComptableDetailComponent} from './cabinet-comptable-detail.component';
import {CabinetComptableUpdateComponent} from './cabinet-comptable-update.component';
import {CabinetComptableDeletePopupComponent} from './cabinet-comptable-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class CabinetComptableResolve implements Resolve<ICabinetComptable> {
    constructor(private service: CabinetComptableService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICabinetComptable> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CabinetComptable>) => response.ok),
                map((cabinetComptable: HttpResponse<CabinetComptable>) => cabinetComptable.body)
            );
        }
        return of(new CabinetComptable());
    }
}

export const cabinetComptableRoute: Routes = [
    {
        path: '',
        component: CabinetComptableComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.cabinetComptable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CabinetComptableDetailComponent,
        resolve: {
            cabinetComptable: CabinetComptableResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.cabinetComptable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CabinetComptableUpdateComponent,
        resolve: {
            cabinetComptable: CabinetComptableResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.cabinetComptable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CabinetComptableUpdateComponent,
        resolve: {
            cabinetComptable: CabinetComptableResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.cabinetComptable.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cabinetComptablePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CabinetComptableDeletePopupComponent,
        resolve: {
            cabinetComptable: CabinetComptableResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.cabinetComptable.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
