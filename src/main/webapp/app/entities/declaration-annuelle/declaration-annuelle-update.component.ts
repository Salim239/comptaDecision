import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {JhiAlertService} from 'ng-jhipster';
import {IDeclarationAnnuelle} from 'app/shared/model/declaration-annuelle.model';
import {DeclarationAnnuelleService} from './declaration-annuelle.service';
import {IFicheClient} from 'app/shared/model/fiche-client.model';
import {FicheClientService} from 'app/entities/fiche-client';
import ComptaDecisionUtils from "app/shared/util/compta-decision-utils";
import * as _ from 'lodash';

@Component({
    selector: 'jhi-declaration-annuelle-update',
    templateUrl: './declaration-annuelle-update.component.html'
})
export class DeclarationAnnuelleUpdateComponent implements OnInit {
    declarationAnnuelle: IDeclarationAnnuelle;
    isSaving: boolean;

    ficheclients: IFicheClient[];
    datePaiementDp: any;
    currentYear: number;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected declarationAnnuelleService: DeclarationAnnuelleService,
        protected ficheClientService: FicheClientService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        let that = this;
        // _.each(this.declarationAnnuelle.declarationAnnuelleDetails, function(declarationAnnuelleDetail) {
        //     that.formatMontant(declarationAnnuelleDetail) ;
        // });
        this.currentYear = moment().year();
        this.activatedRoute.data.subscribe(({ declarationAnnuelle, ficheClients }) => {
            this.declarationAnnuelle = declarationAnnuelle;

        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.unformatDeclarationAnnuelle();
        if (this.declarationAnnuelle.id !== undefined) {
            this.subscribeToSaveResponse(this.declarationAnnuelleService.update(this.declarationAnnuelle));
        } else {
            this.subscribeToSaveResponse(this.declarationAnnuelleService.create(this.declarationAnnuelle));
        }
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

    private formatMontant(declarationAnnuelleDetail) {
        declarationAnnuelleDetail.montant = ComptaDecisionUtils.parseCurrency(declarationAnnuelleDetail.montant);
        declarationAnnuelleDetail.montant = ComptaDecisionUtils.formatCurrency(declarationAnnuelleDetail.montant);
    }

    private parseMontant(declarationAnnuelleDetail) {
        declarationAnnuelleDetail.montantBase = ComptaDecisionUtils.parseCurrency(declarationAnnuelleDetail.montant);
    }

    calculerMontant(indexDetail) {
        this.unformatMontantDeclarationAnnuelle();
        this.declarationAnnuelle.montant = _.sum(_.map(this.declarationAnnuelle.declarationAnnuelleDetails, function (declarationAnnuelleDetail) {
            return declarationAnnuelleDetail.montant ? declarationAnnuelleDetail.montant : 0;
        }));
        this.formatMontantDeclarationAnnuelle()
    }

    private unformatMontantDeclarationAnnuelle() {
        _.each(this.declarationAnnuelle.declarationAnnuelleDetails, function(declarationAnnuelleDetail) {
            declarationAnnuelleDetail.montant = ComptaDecisionUtils.parseCurrency(declarationAnnuelleDetail.montant);
        });
    }

    private formatMontantDeclarationAnnuelle() {
        _.each(this.declarationAnnuelle.declarationAnnuelleDetails, function(declarationAnnuelleDetail) {
            declarationAnnuelleDetail.montant = ComptaDecisionUtils.formatCurrency(declarationAnnuelleDetail.montant);
        });
    }
}
