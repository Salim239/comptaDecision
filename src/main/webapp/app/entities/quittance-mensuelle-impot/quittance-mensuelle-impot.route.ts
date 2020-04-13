import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {IQuittanceMensuelleImpot, QuittanceMensuelleImpot} from 'app/shared/model/quittance-mensuelle-impot.model';
import {QuittanceMensuelleImpotService} from './quittance-mensuelle-impot.service';
import {QuittanceMensuelleImpotComponent} from './quittance-mensuelle-impot.component';
import {QuittanceMensuelleImpotDetailComponent} from './quittance-mensuelle-impot-detail.component';
import {QuittanceMensuelleImpotUpdateComponent} from './quittance-mensuelle-impot-update.component';
import {QuittanceMensuelleImpotDeletePopupComponent} from './quittance-mensuelle-impot-delete-dialog.component';
import {IFicheClient} from 'app/shared/model/fiche-client.model';
import {FicheClientService} from 'app/entities/fiche-client';

@Injectable({ providedIn: 'root' })
export class QuittanceMensuelleImpotResolve implements Resolve<IQuittanceMensuelleImpot> {
    constructor(private service: QuittanceMensuelleImpotService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IQuittanceMensuelleImpot> {
        const id = route.params['id'] ? route.params['id'] : null;
        const annee = route.params['annee'] ? route.params['annee'] : null;
        const mois = route.params['mois'] ? route.params['mois'] : null;
        const typeDeclaration = route.params['typeDeclaration'] ? route.params['typeDeclaration'] : null;
        if (id && !annee && !mois) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<QuittanceMensuelleImpot>) => response.ok),
                map((quittanceMensuelleImpot: HttpResponse<QuittanceMensuelleImpot>) => quittanceMensuelleImpot.body),

            );
        }

        if (id && annee && mois && typeDeclaration) {
            return this.service.initEmpty(id, annee, mois, typeDeclaration)
                .pipe(
                    filter((response: HttpResponse<QuittanceMensuelleImpot>) => response.ok),
                    map((quittanceMensuelleImpot: HttpResponse<QuittanceMensuelleImpot>) => {
                        return quittanceMensuelleImpot.body;
                    })
                );
        }
        // return of(new QuittanceMensuelleImpot());
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

export const quittanceMensuelleImpotRoute: Routes = [
    {
        path: '',
        component: QuittanceMensuelleImpotComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpot.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: QuittanceMensuelleImpotDetailComponent,
        resolve: {
            quittanceMensuelleImpot: QuittanceMensuelleImpotResolve,
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpot.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/:annee/:mois/:typeDeclaration/new',
        component: QuittanceMensuelleImpotUpdateComponent,
        resolve: {
            quittanceMensuelleImpot: QuittanceMensuelleImpotResolve,
            ficheClients: FicheClientResolve

        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpot.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: QuittanceMensuelleImpotUpdateComponent,
        resolve: {
            quittanceMensuelleImpot: QuittanceMensuelleImpotResolve,
            ficheClients: FicheClientResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpot.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const quittanceMensuelleImpotPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: QuittanceMensuelleImpotDeletePopupComponent,
        resolve: {
            quittanceMensuelleImpot: QuittanceMensuelleImpotResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.quittanceMensuelleImpot.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
