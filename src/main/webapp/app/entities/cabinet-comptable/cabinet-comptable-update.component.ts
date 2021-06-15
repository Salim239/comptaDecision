import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {JhiAlertService} from 'ng-jhipster';
import {ICabinetComptable} from 'app/shared/model/cabinet-comptable.model';
import {CabinetComptableService} from './cabinet-comptable.service';
import {IFicheClient} from 'app/shared/model/fiche-client.model';
import {FicheClientService} from 'app/entities/fiche-client';

@Component({
    selector: 'jhi-cabinet-comptable-update',
    templateUrl: './cabinet-comptable-update.component.html'
})
export class CabinetComptableUpdateComponent implements OnInit {
    cabinetComptable: ICabinetComptable;
    isSaving: boolean;

    ficheclientcabinets: IFicheClient[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected cabinetComptableService: CabinetComptableService,
        protected ficheClientService: FicheClientService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cabinetComptable }) => {
            this.cabinetComptable = cabinetComptable;
        });
        this.ficheClientService
            .query({ filter: 'clienttypecomptableinfo-is-null' })
            .pipe(
                filter((mayBeOk: HttpResponse<IFicheClient[]>) => mayBeOk.ok),
                map((response: HttpResponse<IFicheClient[]>) => response.body)
            )
            .subscribe(
                (res: IFicheClient[]) => {
                    if (!this.cabinetComptable.ficheClientCabinetId) {
                        this.ficheclientcabinets = res;
                    } else {
                        this.ficheClientService
                            .find(this.cabinetComptable.ficheClientCabinetId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<IFicheClient>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<IFicheClient>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: IFicheClient) => (this.ficheclientcabinets = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.cabinetComptable.id !== undefined) {
            this.subscribeToSaveResponse(this.cabinetComptableService.update(this.cabinetComptable));
        } else {
            this.subscribeToSaveResponse(this.cabinetComptableService.create(this.cabinetComptable));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICabinetComptable>>) {
        result.subscribe((res: HttpResponse<ICabinetComptable>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
