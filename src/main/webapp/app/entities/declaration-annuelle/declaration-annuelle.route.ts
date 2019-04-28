import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DeclarationAnnuelle } from 'app/shared/model/declaration-annuelle.model';
import { DeclarationAnnuelleService } from './declaration-annuelle.service';
import { DeclarationAnnuelleComponent } from './declaration-annuelle.component';
import { DeclarationAnnuelleDetailComponent } from './declaration-annuelle-detail.component';
import { DeclarationAnnuelleUpdateComponent } from './declaration-annuelle-update.component';
import { DeclarationAnnuelleDeletePopupComponent } from './declaration-annuelle-delete-dialog.component';
import { IDeclarationAnnuelle } from 'app/shared/model/declaration-annuelle.model';
import {IFicheClient} from "app/shared/model/fiche-client.model";
import {FicheClientService} from "app/entities/fiche-client";

@Injectable({ providedIn: 'root' })
export class DeclarationAnnuelleResolve implements Resolve<IDeclarationAnnuelle> {
    constructor(private service: DeclarationAnnuelleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IDeclarationAnnuelle> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DeclarationAnnuelle>) => response.ok),
                map((declarationAnnuelle: HttpResponse<DeclarationAnnuelle>) => declarationAnnuelle.body)
            );
        }
        return of(new DeclarationAnnuelle());
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

export const declarationAnnuelleRoute: Routes = [
    {
        path: '',
        component: DeclarationAnnuelleComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.declarationAnnuelle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: DeclarationAnnuelleDetailComponent,
        resolve: {
            declarationAnnuelle: DeclarationAnnuelleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.declarationAnnuelle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: DeclarationAnnuelleUpdateComponent,
        resolve: {
            declarationAnnuelle: DeclarationAnnuelleResolve,
            ficheClients: FicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.declarationAnnuelle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: DeclarationAnnuelleUpdateComponent,
        resolve: {
            declarationAnnuelle: DeclarationAnnuelleResolve,
            ficheClients: FicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.declarationAnnuelle.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const declarationAnnuellePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: DeclarationAnnuelleDeletePopupComponent,
        resolve: {
            declarationAnnuelle: DeclarationAnnuelleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.declarationAnnuelle.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
