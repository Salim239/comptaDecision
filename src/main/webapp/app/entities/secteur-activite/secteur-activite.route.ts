import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { SecteurActivite } from 'app/shared/model/secteur-activite.model';
import { SecteurActiviteService } from './secteur-activite.service';
import { SecteurActiviteComponent } from './secteur-activite.component';
import { SecteurActiviteLigneComponent } from './secteur-activite-detail.component';
import { SecteurActiviteUpdateComponent } from './secteur-activite-update.component';
import { SecteurActiviteDeletePopupComponent } from './secteur-activite-delete-dialog.component';
import { ISecteurActivite } from 'app/shared/model/secteur-activite.model';

@Injectable({ providedIn: 'root' })
export class SecteurActiviteResolve implements Resolve<ISecteurActivite> {
    constructor(private service: SecteurActiviteService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ISecteurActivite> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<SecteurActivite>) => response.ok),
                map((secteurActivite: HttpResponse<SecteurActivite>) => secteurActivite.body)
            );
        }
        return of(new SecteurActivite());
    }
}

export const secteurActiviteRoute: Routes = [
    {
        path: '',
        component: SecteurActiviteComponent,
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.secteurActivite.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: SecteurActiviteLigneComponent,
        resolve: {
            secteurActivite: SecteurActiviteResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.secteurActivite.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: SecteurActiviteUpdateComponent,
        resolve: {
            secteurActivite: SecteurActiviteResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.secteurActivite.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: SecteurActiviteUpdateComponent,
        resolve: {
            secteurActivite: SecteurActiviteResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.secteurActivite.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const secteurActivitePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: SecteurActiviteDeletePopupComponent,
        resolve: {
            secteurActivite: SecteurActiviteResolve
        },
        data: {
            authorities: ['ROLE_ADMIN'],
            pageTitle: 'comptaDecisionApp.secteurActivite.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
