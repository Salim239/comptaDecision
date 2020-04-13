import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {JhiAlertService} from 'ng-jhipster';
import {IDeclarationEmployeurAnnuelle} from 'app/shared/model/declaration-employeur-annuelle.model';
import {DeclarationEmployeurAnnuelleService} from './declaration-employeur-annuelle.service';
import {IFicheClient} from 'app/shared/model/fiche-client.model';
import {FicheClientService} from 'app/entities/fiche-client';
import * as moment from 'moment';
import ComptaDecisionUtils from 'app/shared/util/compta-decision-utils';

@Component({
    selector: 'jhi-declaration-employeur-annuelle-update',
    templateUrl: './declaration-employeur-annuelle-update.component.html'
})
export class DeclarationEmployeurAnnuelleUpdateComponent implements OnInit {
    declarationEmployeurAnnuelle: IDeclarationEmployeurAnnuelle;
    isSaving: boolean;
    currentYear: number;
    ficheclients: IFicheClient[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected declarationEmployeurAnnuelleService: DeclarationEmployeurAnnuelleService,
        protected ficheClientService: FicheClientService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.currentYear = moment().year();
        this.activatedRoute.data.subscribe(({ declarationEmployeurAnnuelle}) => {
            this.declarationEmployeurAnnuelle = declarationEmployeurAnnuelle;
            this.formatMontants();
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
        if (this.declarationEmployeurAnnuelle.id) {
            this.subscribeToSaveResponse(this.declarationEmployeurAnnuelleService.update(this.declarationEmployeurAnnuelle));
        } else {
            this.subscribeToSaveResponse(this.declarationEmployeurAnnuelleService.create(this.declarationEmployeurAnnuelle));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeclarationEmployeurAnnuelle>>) {
        result.subscribe(
            (res: HttpResponse<IDeclarationEmployeurAnnuelle>) => this.onSaveSuccess(),
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

    updateMontants() {
        this.parseMontants();
        this.formatMontants();
    }

    parseMontants() {

        this.declarationEmployeurAnnuelle.montantAnnexe1 = ComptaDecisionUtils.parseCurrency(this.declarationEmployeurAnnuelle.montantAnnexe1);
        this.declarationEmployeurAnnuelle.montantAnnexe2 = ComptaDecisionUtils.parseCurrency(this.declarationEmployeurAnnuelle.montantAnnexe2);
        this.declarationEmployeurAnnuelle.montantAnnexe3 = ComptaDecisionUtils.parseCurrency(this.declarationEmployeurAnnuelle.montantAnnexe3);
        this.declarationEmployeurAnnuelle.montantAnnexe4 = ComptaDecisionUtils.parseCurrency(this.declarationEmployeurAnnuelle.montantAnnexe4);
        this.declarationEmployeurAnnuelle.montantAnnexe5 = ComptaDecisionUtils.parseCurrency(this.declarationEmployeurAnnuelle.montantAnnexe5);
        this.declarationEmployeurAnnuelle.montantAnnexe6 = ComptaDecisionUtils.parseCurrency(this.declarationEmployeurAnnuelle.montantAnnexe6);
        this.declarationEmployeurAnnuelle.montantAnnexe7 = ComptaDecisionUtils.parseCurrency(this.declarationEmployeurAnnuelle.montantAnnexe7);
        this.declarationEmployeurAnnuelle.montantAnnexe8 = ComptaDecisionUtils.parseCurrency(this.declarationEmployeurAnnuelle.montantAnnexe8);
        this.declarationEmployeurAnnuelle.montantAnnexe9 = ComptaDecisionUtils.parseCurrency(this.declarationEmployeurAnnuelle.montantAnnexe9);
        this.declarationEmployeurAnnuelle.montantAnnexe10 = ComptaDecisionUtils.parseCurrency(this.declarationEmployeurAnnuelle.montantAnnexe10);
        this.declarationEmployeurAnnuelle.montantAnnexe11 = ComptaDecisionUtils.parseCurrency(this.declarationEmployeurAnnuelle.montantAnnexe11);
        this.declarationEmployeurAnnuelle.montantAnnexe12 = ComptaDecisionUtils.parseCurrency(this.declarationEmployeurAnnuelle.montantAnnexe12);
}

    formatMontants() {
        this.declarationEmployeurAnnuelle.montantAnnexe1 = ComptaDecisionUtils.formatCurrency(this.declarationEmployeurAnnuelle.montantAnnexe1);
        this.declarationEmployeurAnnuelle.montantAnnexe2 = ComptaDecisionUtils.formatCurrency(this.declarationEmployeurAnnuelle.montantAnnexe2);
        this.declarationEmployeurAnnuelle.montantAnnexe3 = ComptaDecisionUtils.formatCurrency(this.declarationEmployeurAnnuelle.montantAnnexe3);
        this.declarationEmployeurAnnuelle.montantAnnexe4 = ComptaDecisionUtils.formatCurrency(this.declarationEmployeurAnnuelle.montantAnnexe4);
        this.declarationEmployeurAnnuelle.montantAnnexe5 = ComptaDecisionUtils.formatCurrency(this.declarationEmployeurAnnuelle.montantAnnexe5);
        this.declarationEmployeurAnnuelle.montantAnnexe6 = ComptaDecisionUtils.formatCurrency(this.declarationEmployeurAnnuelle.montantAnnexe6);
        this.declarationEmployeurAnnuelle.montantAnnexe7 = ComptaDecisionUtils.formatCurrency(this.declarationEmployeurAnnuelle.montantAnnexe7);
        this.declarationEmployeurAnnuelle.montantAnnexe8 = ComptaDecisionUtils.formatCurrency(this.declarationEmployeurAnnuelle.montantAnnexe8);
        this.declarationEmployeurAnnuelle.montantAnnexe9 = ComptaDecisionUtils.formatCurrency(this.declarationEmployeurAnnuelle.montantAnnexe9);
        this.declarationEmployeurAnnuelle.montantAnnexe10 = ComptaDecisionUtils.formatCurrency(this.declarationEmployeurAnnuelle.montantAnnexe10);
        this.declarationEmployeurAnnuelle.montantAnnexe11 = ComptaDecisionUtils.formatCurrency(this.declarationEmployeurAnnuelle.montantAnnexe11);
        this.declarationEmployeurAnnuelle.montantAnnexe12 = ComptaDecisionUtils.formatCurrency(this.declarationEmployeurAnnuelle.montantAnnexe12);
    }
}
