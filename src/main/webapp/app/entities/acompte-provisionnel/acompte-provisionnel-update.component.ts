import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IAcompteProvisionnel } from 'app/shared/model/acompte-provisionnel.model';
import { AcompteProvisionnelService } from './acompte-provisionnel.service';
import { IFicheClient } from 'app/shared/model/fiche-client.model';
import { FicheClientService } from 'app/entities/fiche-client';
import ComptaDecisionUtils from 'app/shared/util/compta-decision-utils';

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
            this.formatMontants();
            this.calculerMontants();
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
        this.parseMontants();
        if (this.acompteProvisionnel.id) {
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

    calculerMontants() {
        this.parseMontants();
        this.acompteProvisionnel.montantAcompteProvisionnel = this.acompteProvisionnel.montantBase * 0.3;
        this.acompteProvisionnel.montantNet =
            this.acompteProvisionnel.montantAcompteProvisionnel -
            this.acompteProvisionnel.montantReportAnterieur -
            this.acompteProvisionnel.montantPenalite -
            this.acompteProvisionnel.montantRetenueSource;
        this.formatMontants();
    }

    parseMontants() {
        this.acompteProvisionnel.montantPenalite = ComptaDecisionUtils.parseCurrency(this.acompteProvisionnel.montantPenalite);
        this.acompteProvisionnel.montantBase = ComptaDecisionUtils.parseCurrency(this.acompteProvisionnel.montantBase);
        this.acompteProvisionnel.montantNet = ComptaDecisionUtils.parseCurrency(this.acompteProvisionnel.montantNet);
        this.acompteProvisionnel.montantReportAnterieur = ComptaDecisionUtils.parseCurrency(
            this.acompteProvisionnel.montantReportAnterieur
        );
        this.acompteProvisionnel.montantAcompteProvisionnel = ComptaDecisionUtils.parseCurrency(
            this.acompteProvisionnel.montantAcompteProvisionnel
        );
        this.acompteProvisionnel.montantRetenueSource = ComptaDecisionUtils.parseCurrency(this.acompteProvisionnel.montantRetenueSource);
        if (this.acompteProvisionnel.montantBaseCalc !== undefined) {
            this.acompteProvisionnel.montantBaseCalc = ComptaDecisionUtils.parseCurrency(this.acompteProvisionnel.montantBaseCalc);
        }
        if (this.acompteProvisionnel.montantReportAnterieurCalc !== undefined) {
            this.acompteProvisionnel.montantReportAnterieurCalc = ComptaDecisionUtils.parseCurrency(
                this.acompteProvisionnel.montantReportAnterieurCalc
            );
        }
    }

    formatMontants() {
        this.acompteProvisionnel.montantPenalite = ComptaDecisionUtils.formatCurrency(this.acompteProvisionnel.montantPenalite);
        this.acompteProvisionnel.montantBase = ComptaDecisionUtils.formatCurrency(this.acompteProvisionnel.montantBase);

        this.acompteProvisionnel.montantNet = ComptaDecisionUtils.formatCurrency(this.acompteProvisionnel.montantNet);
        this.acompteProvisionnel.montantReportAnterieur = ComptaDecisionUtils.formatCurrency(
            this.acompteProvisionnel.montantReportAnterieur
        );
        this.acompteProvisionnel.montantAcompteProvisionnel = ComptaDecisionUtils.formatCurrency(
            this.acompteProvisionnel.montantAcompteProvisionnel
        );
        this.acompteProvisionnel.montantRetenueSource = ComptaDecisionUtils.formatCurrency(this.acompteProvisionnel.montantRetenueSource);

        if (this.acompteProvisionnel.montantBaseCalc !== undefined) {
            this.acompteProvisionnel.montantBaseCalc = ComptaDecisionUtils.formatCurrency(this.acompteProvisionnel.montantBaseCalc);
        }
        if (this.acompteProvisionnel.montantReportAnterieurCalc !== undefined) {
            this.acompteProvisionnel.montantReportAnterieurCalc = ComptaDecisionUtils.formatCurrency(
                this.acompteProvisionnel.montantReportAnterieurCalc
            );
        }
    }
}
