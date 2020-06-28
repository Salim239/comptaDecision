import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CategorieCnssGerant, ICategorieCnssGerant } from 'app/shared/model/categorie-cnss-gerant.model';
import { CategorieCnssGerantService } from './categorie-cnss-gerant.service';
import { CategorieCnssGerantComponent } from './categorie-cnss-gerant.component';
import { CategorieCnssGerantDetailComponent } from './categorie-cnss-gerant-detail.component';
import { CategorieCnssGerantUpdateComponent } from './categorie-cnss-gerant-update.component';
import { categorieCnssGerantDeletePopupComponent } from './categorie-cnss-gerant-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class CategorieCnssGerantResolve implements Resolve<ICategorieCnssGerant> {
    constructor(private service: CategorieCnssGerantService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICategorieCnssGerant> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ICategorieCnssGerant>) => response.ok),
                map((categorieCnssGerant: HttpResponse<ICategorieCnssGerant>) => categorieCnssGerant.body)
            );
        }
        return of(new CategorieCnssGerant());
    }
}

export const categorieCnssGerantRoute: Routes = [
    {
        path: '',
        component: CategorieCnssGerantComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.categorieCnssGerant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CategorieCnssGerantDetailComponent,
        resolve: {
            categorieCnssGerant: CategorieCnssGerantResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.categorieCnssGerant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CategorieCnssGerantUpdateComponent,
        resolve: {
            categorieCnssGerant: CategorieCnssGerantResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.categorieCnssGerant.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CategorieCnssGerantUpdateComponent,
        resolve: {
            categorieCnssGerant: CategorieCnssGerantResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.categorieCnssGerant.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const categorieCnssGerantPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: categorieCnssGerantDeletePopupComponent,
        resolve: {
            categorieCnssGerant: CategorieCnssGerantResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.categorieCnssGerant.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
