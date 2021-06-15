import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {Observable, of} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {ILigneCaisse, LigneCaisse} from 'app/shared/model/ligne-caisse.model';
import {LigneCaisseService} from './ligne-caisse.service';
import {LigneCaisseComponent} from './ligne-caisse.component';
import {LigneCaisseDetailComponent} from './ligne-caisse-detail.component';
import {LigneCaisseUpdateComponent} from './ligne-caisse-update.component';
import {LigneCaisseDeletePopupComponent} from './ligne-caisse-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class LigneCaisseResolve implements Resolve<ILigneCaisse> {
    constructor(private service: LigneCaisseService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ILigneCaisse> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<LigneCaisse>) => response.ok),
                map((ligneCaisse: HttpResponse<LigneCaisse>) => ligneCaisse.body)
            );
        }
        return of(new LigneCaisse());
    }
}

export const ligneCaisseRoute: Routes = [
    {
        path: '',
        component: LigneCaisseComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.ligneCaisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: LigneCaisseDetailComponent,
        resolve: {
            ligneCaisse: LigneCaisseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.ligneCaisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: LigneCaisseUpdateComponent,
        resolve: {
            ligneCaisse: LigneCaisseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.ligneCaisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: LigneCaisseUpdateComponent,
        resolve: {
            ligneCaisse: LigneCaisseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.ligneCaisse.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ligneCaissePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: LigneCaisseDeletePopupComponent,
        resolve: {
            ligneCaisse: LigneCaisseResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.ligneCaisse.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
