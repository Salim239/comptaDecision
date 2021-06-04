import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IDeclarationAnnuelle } from 'app/shared/model/declaration-annuelle.model';
import { DeclarationAnnuelleService } from './declaration-annuelle.service';
import { IFicheClient } from 'app/shared/model/fiche-client.model';
import { FicheClientService } from 'app/entities/fiche-client';
import ComptaDecisionUtils from 'app/shared/util/compta-decision-utils';
import * as _ from 'lodash';

@Component({
    selector: 'jhi-declaration-annuelle-update',
    templateUrl: './declaration-annuelle-update.component.html'
})
export class DeclarationAnnuelleUpdateComponent implements OnInit {
    declarationAnnuelle: IDeclarationAnnuelle;
    isSaving: boolean;

    ficheclients: IFicheClient[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected declarationAnnuelleService: DeclarationAnnuelleService,
        protected ficheClientService: FicheClientService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;

        this.activatedRoute.data.subscribe(({ declarationAnnuelle }) => {
            this.declarationAnnuelle = declarationAnnuelle;
            this.formatDeclarationAnnuelle();
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.parseDeclarationAnnuelle();
        if (this.declarationAnnuelle.id) {
            this.subscribeToSaveResponse(this.declarationAnnuelleService.update(this.declarationAnnuelle));
        } else {
            this.subscribeToSaveResponse(this.declarationAnnuelleService.create(this.declarationAnnuelle));
        }
        this.formatDeclarationAnnuelle();
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeclarationAnnuelle>>) {
        result.subscribe((res: HttpResponse<IDeclarationAnnuelle>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    calculerMontant(indexLigne) {
        this.declarationAnnuelle.montantNet = _.sum(
            _.map(this.declarationAnnuelle.declarationAnnuelleLignes, function(declarationAnnuelleLigne) {
                return declarationAnnuelleLigne.montant ? ComptaDecisionUtils.parseCurrency(declarationAnnuelleLigne.montant) : 0;
            })
        );
        this.declarationAnnuelle.declarationAnnuelleLignes[indexLigne].montant = ComptaDecisionUtils.formatCurrency(
            this.declarationAnnuelle.declarationAnnuelleLignes[indexLigne].montant
        );
        this.calculerMontants();
    }

    private parseDeclarationAnnuelle() {
        _.each(this.declarationAnnuelle.declarationAnnuelleLignes, function(declarationAnnuelleLigne) {
            declarationAnnuelleLigne.montant = ComptaDecisionUtils.parseCurrency(declarationAnnuelleLigne.montant);
        });
        this.parseMontants();
    }

    private formatDeclarationAnnuelle() {
        _.each(this.declarationAnnuelle.declarationAnnuelleLignes, function(declarationAnnuelleLigne) {
            declarationAnnuelleLigne.montant = ComptaDecisionUtils.formatCurrency(declarationAnnuelleLigne.montant);
        });
        this.formatMontants();
    }

    calculerMontants() {
        this.parseMontants();
        this.declarationAnnuelle.montantNet =
            this.declarationAnnuelle.montantImpotAnnuel +
            this.declarationAnnuelle.montantRetenueSource -
            this.declarationAnnuelle.montantApPayes -
            this.declarationAnnuelle.montantReportAnterieur;
        this.formatMontants();
    }

    parseMontants() {
        this.declarationAnnuelle.montantImpotAnnuel = ComptaDecisionUtils.parseCurrency(this.declarationAnnuelle.montantImpotAnnuel);
        this.declarationAnnuelle.montantApPayes = ComptaDecisionUtils.parseCurrency(this.declarationAnnuelle.montantApPayes);
        this.declarationAnnuelle.montantReportAnterieur = ComptaDecisionUtils.parseCurrency(
            this.declarationAnnuelle.montantReportAnterieur
        );
        this.declarationAnnuelle.montantRetenueSource = ComptaDecisionUtils.parseCurrency(this.declarationAnnuelle.montantRetenueSource);
        this.declarationAnnuelle.montantNet = ComptaDecisionUtils.parseCurrency(this.declarationAnnuelle.montantNet);
    }

    formatMontants() {
        this.declarationAnnuelle.montantImpotAnnuel = ComptaDecisionUtils.formatCurrency(this.declarationAnnuelle.montantImpotAnnuel);
        this.declarationAnnuelle.montantApPayes = ComptaDecisionUtils.formatCurrency(this.declarationAnnuelle.montantApPayes);
        this.declarationAnnuelle.montantReportAnterieur = ComptaDecisionUtils.formatCurrency(
            this.declarationAnnuelle.montantReportAnterieur
        );
        this.declarationAnnuelle.montantRetenueSource = ComptaDecisionUtils.formatCurrency(this.declarationAnnuelle.montantRetenueSource);
        this.declarationAnnuelle.montantNet = ComptaDecisionUtils.formatCurrency(this.declarationAnnuelle.montantNet);
    }
}
