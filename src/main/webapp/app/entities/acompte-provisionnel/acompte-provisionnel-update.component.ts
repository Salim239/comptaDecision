import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IAcompteProvisionnel } from 'app/shared/model/acompte-provisionnel.model';
import { AcompteProvisionnelService } from './acompte-provisionnel.service';
import { IFicheClient } from 'app/shared/model/fiche-client.model';
import { FicheClientService } from 'app/entities/fiche-client';
import {TypeCnss} from "app/shared/model/cnss.model";

@Component({
    selector: 'jhi-acompte-provisionnel-update',
    templateUrl: './acompte-provisionnel-update.component.html'
})
export class AcompteProvisionnelUpdateComponent implements OnInit {
    acompteProvisionnel: IAcompteProvisionnel;
    isSaving: boolean;
    currentYear: number;
    ficheclients: IFicheClient[];
    dateDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected acompteProvisionnelService: AcompteProvisionnelService,
        protected ficheClientService: FicheClientService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.currentYear = moment().year();
        this.activatedRoute.data.subscribe(({ acompteProvisionnel, ficheClients }) => {
            this.acompteProvisionnel = acompteProvisionnel;
            this.ficheclients = ficheClients;
            if (!this.acompteProvisionnel.id) {
                if (this.ficheclients.length > 0) {
                    this.acompteProvisionnel.ficheClientId = this.ficheclients[0].id;
                    this.acompteProvisionnel.ficheClientDateCreation = this.ficheclients[0].dateCreation;
                    this.acompteProvisionnel.ficheClientDesignation = this.ficheclients[0].designation;
                    this.acompteProvisionnel.ficheClientMatriculeFiscale = this.ficheclients[0].matriculeFiscale;
                    this.acompteProvisionnel.ficheClientRegistreCommerce = this.ficheclients[0].registreCommerce;
                }
                this.acompteProvisionnel.annee = this.currentYear;
            }
        });
        this.ficheClientService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IFicheClient[]>) => mayBeOk.ok),
                map((response: HttpResponse<IFicheClient[]>) => response.body)
            )
            .subscribe((res: IFicheClient[]) => (this.ficheclients = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.acompteProvisionnel.id !== undefined) {
            this.subscribeToSaveResponse(this.acompteProvisionnelService.update(this.acompteProvisionnel));
        } else {
            this.subscribeToSaveResponse(this.acompteProvisionnelService.create(this.acompteProvisionnel));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IAcompteProvisionnel>>) {
        result.subscribe((res: HttpResponse<IAcompteProvisionnel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
