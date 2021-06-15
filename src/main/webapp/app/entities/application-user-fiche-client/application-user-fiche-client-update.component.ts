import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {JhiAlertService} from 'ng-jhipster';
import {IApplicationUserFicheClient} from 'app/shared/model/application-user-fiche-client.model';
import {ApplicationUserFicheClientService} from './application-user-fiche-client.service';
import {IFicheClient} from 'app/shared/model/fiche-client.model';
import {FicheClientService} from 'app/entities/fiche-client';
import {IApplicationUser} from 'app/shared/model/application-user.model';
import {ApplicationUserService} from 'app/entities/application-user';

@Component({
    selector: 'jhi-application-user-fiche-client-update',
    templateUrl: './application-user-fiche-client-update.component.html'
})
export class ApplicationUserFicheClientUpdateComponent implements OnInit {
    applicationUserFicheClient: IApplicationUserFicheClient;
    isSaving: boolean;

    ficheclients: IFicheClient[];

    applicationusers: IApplicationUser[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected applicationUserFicheClientService: ApplicationUserFicheClientService,
        protected ficheClientService: FicheClientService,
        protected applicationUserService: ApplicationUserService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ applicationUserFicheClient }) => {
            this.applicationUserFicheClient = applicationUserFicheClient;
        });
        this.ficheClientService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IFicheClient[]>) => mayBeOk.ok),
                map((response: HttpResponse<IFicheClient[]>) => response.body)
            )
            .subscribe((res: IFicheClient[]) => (this.ficheclients = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.applicationUserService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IApplicationUser[]>) => mayBeOk.ok),
                map((response: HttpResponse<IApplicationUser[]>) => response.body)
            )
            .subscribe((res: IApplicationUser[]) => (this.applicationusers = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.applicationUserFicheClient.id !== undefined) {
            this.subscribeToSaveResponse(this.applicationUserFicheClientService.update(this.applicationUserFicheClient));
        } else {
            this.subscribeToSaveResponse(this.applicationUserFicheClientService.create(this.applicationUserFicheClient));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IApplicationUserFicheClient>>) {
        result.subscribe(
            (res: HttpResponse<IApplicationUserFicheClient>) => this.onSaveSuccess(),
            (res: HttpErrorResponse) => this.onSaveError()
        );
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackFicheClientById(index: number, item: IFicheClient) {
        return item.id;
    }

    trackApplicationUserById(index: number, item: IApplicationUser) {
        return item.id;
    }
}
