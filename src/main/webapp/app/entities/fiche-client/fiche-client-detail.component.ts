import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { IFicheClient } from 'app/shared/model/fiche-client.model';
import { IImpotMensuelClient } from 'app/shared/model/impot-mensuel-client.model';
import { ImpotMensuelClientService } from 'app/entities/impot-mensuel-client';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
    selector: 'jhi-fiche-client-detail',
    templateUrl: './fiche-client-detail.component.html'
})
export class FicheClientLigneComponent implements OnInit {
    ficheClient: IFicheClient;
    impotMensuelClient: IImpotMensuelClient;
    isSaving: boolean;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected activatedRoute: ActivatedRoute,
        protected impotMensuelClientService: ImpotMensuelClientService,
        protected jhiAlertService: JhiAlertService
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ficheClient }) => {
            this.ficheClient = ficheClient;
        });
    }

    saveImpotMensuel(impotMensuelClient: IImpotMensuelClient) {
        this.isSaving = true;
        if (impotMensuelClient.id !== undefined) {
            this.subscribeToSaveResponse(this.impotMensuelClientService.update(impotMensuelClient));
        } else {
            this.subscribeToSaveResponse(this.impotMensuelClientService.create(impotMensuelClient));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IImpotMensuelClient>>) {
        result.subscribe((res: HttpResponse<IImpotMensuelClient>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        // this.previousState();
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
