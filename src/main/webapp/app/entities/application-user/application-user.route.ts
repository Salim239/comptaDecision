import {Injectable} from '@angular/core';
import {HttpResponse} from '@angular/common/http';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes} from '@angular/router';
import {JhiResolvePagingParams} from 'ng-jhipster';
import {UserRouteAccessService} from 'app/core';
import {Observable, of} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {ApplicationUser, IApplicationUser} from 'app/shared/model/application-user.model';
import {ApplicationUserService} from './application-user.service';
import {ApplicationUserComponent} from './application-user.component';
import {ApplicationUserDetailComponent} from './application-user-detail.component';
import {ApplicationUserUpdateComponent} from './application-user-update.component';
import {ApplicationUserDeletePopupComponent} from './application-user-delete-dialog.component';

@Injectable({ providedIn: 'root' })
export class ApplicationUserResolve implements Resolve<IApplicationUser> {
    constructor(private service: ApplicationUserService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IApplicationUser> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<ApplicationUser>) => response.ok),
                map((applicationUser: HttpResponse<ApplicationUser>) => applicationUser.body)
            );
        }
        return of(new ApplicationUser());
    }
}

export const applicationUserRoute: Routes = [
    {
        path: '',
        component: ApplicationUserComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'comptaDecisionApp.applicationUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/view',
        component: ApplicationUserDetailComponent,
        resolve: {
            applicationUser: ApplicationUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.applicationUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'new',
        component: ApplicationUserUpdateComponent,
        resolve: {
            applicationUser: ApplicationUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.applicationUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: ':id/edit',
        component: ApplicationUserUpdateComponent,
        resolve: {
            applicationUser: ApplicationUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.applicationUser.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const applicationUserPopupRoute: Routes = [
    {
        path: ':id/delete',
        component: ApplicationUserDeletePopupComponent,
        resolve: {
            applicationUser: ApplicationUserResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'comptaDecisionApp.applicationUser.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
