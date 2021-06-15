import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {JhiAlertService} from 'ng-jhipster';
import {IApplicationUser} from 'app/shared/model/application-user.model';
import {ApplicationUserService} from './application-user.service';
import {ICabinetComptable} from 'app/shared/model/cabinet-comptable.model';
import {CabinetComptableService} from 'app/entities/cabinet-comptable';

@Component({
    selector: 'jhi-application-user-update',
    templateUrl: './application-user-update.component.html'
})
export class ApplicationUserUpdateComponent implements OnInit {
    applicationUser: IApplicationUser;
    isSaving: boolean;

    cabinetcomptables: ICabinetComptable[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected applicationUserService: ApplicationUserService,
        protected cabinetComptableService: CabinetComptableService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ applicationUser }) => {
            this.applicationUser = applicationUser;
        });
        this.cabinetComptableService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICabinetComptable[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICabinetComptable[]>) => response.body)
            )
            .subscribe((res: ICabinetComptable[]) => (this.cabinetcomptables = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.applicationUser.id !== undefined) {
            this.subscribeToSaveResponse(this.applicationUserService.update(this.applicationUser));
        } else {
            this.subscribeToSaveResponse(this.applicationUserService.create(this.applicationUser));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IApplicationUser>>) {
        result.subscribe((res: HttpResponse<IApplicationUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCabinetComptableById(index: number, item: ICabinetComptable) {
        return item.id;
    }
}
