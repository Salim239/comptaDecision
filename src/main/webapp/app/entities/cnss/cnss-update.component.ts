import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { ICnss, TypeCnss } from 'app/shared/model/cnss.model';
import { CnssService } from './cnss.service';
import { IFicheClient } from 'app/shared/model/fiche-client.model';
import { FicheClientService } from 'app/entities/fiche-client';
import ComptaDecisionUtils from 'app/shared/util/compta-decision-utils';

@Component({
    selector: 'jhi-cnss-update',
    templateUrl: './cnss-update.component.html'
})
export class CnssUpdateComponent implements OnInit {
    cnss: ICnss;
    isSaving: boolean;
    ficheclients: IFicheClient[];
    trimestres: any[];
    isTypeCnssGerant: boolean;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected cnssService: CnssService,
        protected ficheClientService: FicheClientService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cnss }) => {
            this.cnss = cnss;
            this.isTypeCnssGerant = this.cnss.typeCnss === TypeCnss.CNSS_EMPLOYEUR;
            this.formatMontants();
        });
        this.trimestres = [
            { id: 1, libelle: 'Trimestre 1' },
            { id: 2, libelle: 'Trimestre 2' },
            { id: 3, libelle: 'Trimestre 3' },
            { id: 4, libelle: 'Trimestre 4' }
        ];
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.calculerMontant();
        this.parseMontants();
        this.cnss.montantTotalSalaireBrut =
            this.cnss.montantSalaireBrutKarama + this.cnss.montantSalaireBrutNormal + this.cnss.montantPenalite;
        if (this.cnss.id) {
            this.subscribeToSaveResponse(this.cnssService.update(this.cnss));
        } else {
            this.subscribeToSaveResponse(this.cnssService.create(this.cnss));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICnss>>) {
        result.subscribe((res: HttpResponse<ICnss>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    parseMontants() {
        this.cnss.montantSalaireBrutKarama = ComptaDecisionUtils.parseCurrency(this.cnss.montantSalaireBrutKarama);
        this.cnss.montantSalaireBrutNormal = ComptaDecisionUtils.parseCurrency(this.cnss.montantSalaireBrutNormal);
        this.cnss.montantCnssNormal = ComptaDecisionUtils.parseCurrency(this.cnss.montantCnssNormal);
        this.cnss.montantCnssKarama = ComptaDecisionUtils.parseCurrency(this.cnss.montantCnssKarama);
        this.cnss.montantTotalSalaireBrut = ComptaDecisionUtils.parseCurrency(this.cnss.montantTotalSalaireBrut);
        this.cnss.montantTotalCnss = ComptaDecisionUtils.parseCurrency(this.cnss.montantTotalCnss);
        this.cnss.totalTauxCnssNormal = ComptaDecisionUtils.parseCurrency(this.cnss.totalTauxCnssNormal);
        this.cnss.totalTauxCnssKarama = ComptaDecisionUtils.parseCurrency(this.cnss.totalTauxCnssKarama);
        this.cnss.montantPenalite = ComptaDecisionUtils.parseCurrency(this.cnss.montantPenalite);
        this.cnss.categorieCnssGerantMontant = ComptaDecisionUtils.parseCurrency(this.cnss.categorieCnssGerantMontant);
    }

    formatMontants() {
        this.cnss.montantSalaireBrutKarama = ComptaDecisionUtils.formatCurrency(this.cnss.montantSalaireBrutKarama);
        this.cnss.montantSalaireBrutNormal = ComptaDecisionUtils.formatCurrency(this.cnss.montantSalaireBrutNormal);
        this.cnss.montantCnssNormal = ComptaDecisionUtils.formatCurrency(this.cnss.montantCnssNormal);
        this.cnss.montantCnssKarama = ComptaDecisionUtils.formatCurrency(this.cnss.montantCnssKarama);
        this.cnss.montantTotalSalaireBrut = ComptaDecisionUtils.formatCurrency(this.cnss.montantTotalSalaireBrut);
        this.cnss.montantTotalCnss = ComptaDecisionUtils.formatCurrency(this.cnss.montantTotalCnss);
        this.cnss.totalTauxCnssNormal = ComptaDecisionUtils.formatCurrency(this.cnss.totalTauxCnssNormal);
        this.cnss.totalTauxCnssKarama = ComptaDecisionUtils.formatCurrency(this.cnss.totalTauxCnssKarama);
        this.cnss.montantPenalite = ComptaDecisionUtils.formatCurrency(this.cnss.montantPenalite);
        this.cnss.categorieCnssGerantMontant = ComptaDecisionUtils.formatCurrency(this.cnss.categorieCnssGerantMontant);
    }

    calculerMontant() {
        this.parseMontants();
        if (!this.isTypeCnssGerant) {
            this.cnss.montantCnssNormal = this.cnss.montantSalaireBrutNormal * (this.cnss.totalTauxCnssNormal / 100);
            this.cnss.montantCnssKarama = this.cnss.montantSalaireBrutKarama * (this.cnss.totalTauxCnssKarama / 100);
            this.cnss.montantTotalCnss = this.cnss.montantCnssNormal + this.cnss.montantCnssKarama + this.cnss.montantPenalite;
            this.cnss.montantTotalSalaireBrut =
                this.cnss.montantSalaireBrutKarama + this.cnss.montantSalaireBrutNormal + this.cnss.montantPenalite;
        } else {
            this.cnss.montantTotalCnss = this.cnss.categorieCnssGerantMontant + this.cnss.montantPenalite;
        }
        this.formatMontants();
    }
}
