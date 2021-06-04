import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IQuittanceMensuelle, QuittanceMensuelle } from 'app/shared/model/quittance-mensuelle.model';
import { QuittanceMensuelleService } from './quittance-mensuelle.service';
import { QuittanceMensuelleComponent } from './quittance-mensuelle.component';
import { QuittanceMensuelleDetailComponent } from './quittance-mensuelle-detail.component';
import { QuittanceMensuelleUpdateComponent } from './quittance-mensuelle-update.component';
import { QuittanceMensuelleDeletePopupComponent } from './quittance-mensuelle-delete-dialog.component';
import { IFicheClient } from 'app/shared/model/fiche-client.model';
import { FicheClientService } from 'app/entities/fiche-client';

@Injectable({ providedIn: 'root' })
export class QuittanceMensuelleResolve implements Resolve<IQuittanceMensuelle> {
    constructor(private service: QuittanceMensuelleService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IQuittanceMensuelle> {
        const id = route.params['id'] ? route.params['id'] : null;
        const annee = route.params['annee'] ? route.params['annee'] : null;
        const mois = route.params['mois'] ? route.params['mois'] : null;
        const typeDeclaration = route.params['typeDeclaration'] ? route.params['typeDeclaration'] : null;
        if (id && !annee && !mois) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<QuittanceMensuelle>) => response.ok),
                map((quittanceMensuelle: HttpResponse<QuittanceMensuelle>) => quittanceMensuelle.body)
            );
        }

        if (id && annee && mois && typeDeclaration) {
            return this.service.initEmpty(id, annee, mois, typeDeclaration).pipe(
                filter((response: HttpResponse<QuittanceMensuelle>) => response.ok),
                map((quittanceMensuelle: HttpResponse<QuittanceMensuelle>) => {
                    return quittanceMensuelle.body;
                })
            );
        }
        // return of(new QuittanceMensuelle());
    }
}

@Injectable({ providedIn: 'root' })
export class FicheClientResolve implements Resolve<IFicheClient[]> {
    constructor(private service: FicheClientService) {}

    resolve(): Observable<IFicheClient[]> {
        return this.service.query().pipe(
            filter((mayBeOk: HttpResponse<IFicheClient[]>) => mayBeOk.ok),
            map((response: HttpResponse<IFicheClient[]>) => response.body)
        );
    }
}

export const quittanceMensuelleRoute: Routes = [
    {
        path: '',
        component: QuittanceMensuelleComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.quittanceMensuelle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: QuittanceMensuelleDetailComponent,
        resolve: {
            quittanceMensuelle: QuittanceMensuelleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/:annee/:mois/:typeDeclaration/new',
        component: QuittanceMensuelleUpdateComponent,
        resolve: {
            quittanceMensuelle: QuittanceMensuelleResolve,
            ficheClients: FicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelle.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: QuittanceMensuelleUpdateComponent,
        resolve: {
            quittanceMensuelle: QuittanceMensuelleResolve,
            ficheClients: FicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelle.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const quittanceMensuellePopupRoute: Routes = [
    {
        path: ':id/delete',
        component: QuittanceMensuelleDeletePopupComponent,
        resolve: {
            quittanceMensuelle: QuittanceMensuelleResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelle.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
