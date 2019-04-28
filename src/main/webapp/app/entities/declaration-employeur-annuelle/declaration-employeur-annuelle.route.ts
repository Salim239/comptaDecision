import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DeclarationEmployeurAnnuelle } from 'app/shared/model/declaration-employeur-annuelle.model';
import { DeclarationEmployeurAnnuelleService } from './declaration-employeur-annuelle.service';
import { DeclarationEmployeurAnnuelleComponent } from './declaration-employeur-annuelle.component';
import { DeclarationEmployeurAnnuelleDetailComponent } from './declaration-employeur-annuelle-detail.component';
import { DeclarationEmployeurAnnuelleUpdateComponent } from './declaration-employeur-annuelle-update.component';
import { DeclarationEmployeurAnnuelleDeletePopupComponent } from './declaration-employeur-annuelle-delete-dialog.component';
import { IDeclarationEmployeurAnnuelle } from 'app/shared/model/declaration-employeur-annuelle.model';
import {IFicheClient} from "app/shared/model/fiche-client.model";
import {FicheClientService} from "app/entities/fiche-client";

@Injectable({ providedIn: 'root' })
export class DeclarationEmployeurAnnuelleResolve implements Resolve<IDeclarationEmployeurAnnuelle> {
    constructor(private service: DeclarationEmployeurAnnuelleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDeclarationEmployeurAnnuelle> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DeclarationEmployeurAnnuelle>) => response.ok),
                map((declarationEmployeurAnnuelle: HttpResponse<DeclarationEmployeurAnnuelle>) => declarationEmployeurAnnuelle.body)
            );
        }
        return of(new DeclarationEmployeurAnnuelle());
    }
}

@Injectable({ providedIn: 'root' })
export class FicheClientResolve implements Resolve<IFicheClient[]> {
    constructor(private service: FicheClientService) {}

    resolve(): Observable<IFicheClient[]> {

        return this.service
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IFicheClient[]>) => mayBeOk.ok),
                map((response: HttpResponse<IFicheClient[]>) => response.body)
            );
    }
}

export const declarationEmployeurAnnuelleRoute: Routes = [
    {
        path: '',
        component: DeclarationEmployeurAnnuelleComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.declarationEmployeurAnnuelle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: DeclarationEmployeurAnnuelleDetailComponent,
        resolve: {
            declarationEmployeurAnnuelle: DeclarationEmployeurAnnuelleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.declarationEmployeurAnnuelle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: DeclarationEmployeurAnnuelleUpdateComponent,
        resolve: {
            declarationEmployeurAnnuelle: DeclarationEmployeurAnnuelleResolve,
            ficheClients: FicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.declarationEmployeurAnnuelle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: DeclarationEmployeurAnnuelleUpdateComponent,
        resolve: {
            declarationEmployeurAnnuelle: DeclarationEmployeurAnnuelleResolve,
            ficheClients: FicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.declarationEmployeurAnnuelle.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const declarationEmployeurAnnuellePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: DeclarationEmployeurAnnuelleDeletePopupComponent,
        resolve: {
            declarationEmployeurAnnuelle: DeclarationEmployeurAnnuelleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.declarationEmployeurAnnuelle.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
