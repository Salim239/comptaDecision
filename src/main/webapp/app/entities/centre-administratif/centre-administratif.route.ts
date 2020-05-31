import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CentreAdministratif } from 'app/shared/model/centre-administratif.model';
import { CentreAdministratifService } from './centre-administratif.service';
import { CentreAdministratifComponent } from './centre-administratif.component';
import { CentreAdministratifDetailComponent } from './centre-administratif-detail.component';
import { CentreAdministratifUpdateComponent } from './centre-administratif-update.component';
import { CentreAdministratifDeletePopupComponent } from './centre-administratif-delete-dialog.component';
import { ICentreAdministratif } from 'app/shared/model/centre-administratif.model';

@Injectable({ providedIn: 'root' })
export class CentreAdministratifResolve implements Resolve<ICentreAdministratif> {
    constructor(private service: CentreAdministratifService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ICentreAdministratif> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CentreAdministratif>) => response.ok),
                map((centreAdministratif: HttpResponse<CentreAdministratif>) => centreAdministratif.body)
            );
        }
        return of(new CentreAdministratif());
    }
}

export const centreAdministratifRoute: Routes = [
    {
        path: '',
        component: CentreAdministratifComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.centreAdministratif.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: CentreAdministratifDetailComponent,
        resolve: {
            centreAdministratif: CentreAdministratifResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.centreAdministratif.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: CentreAdministratifUpdateComponent,
        resolve: {
            centreAdministratif: CentreAdministratifResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.centreAdministratif.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: CentreAdministratifUpdateComponent,
        resolve: {
            centreAdministratif: CentreAdministratifResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.centreAdministratif.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const centreAdministratifPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: CentreAdministratifDeletePopupComponent,
        resolve: {
            centreAdministratif: CentreAdministratifResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.centreAdministratif.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
