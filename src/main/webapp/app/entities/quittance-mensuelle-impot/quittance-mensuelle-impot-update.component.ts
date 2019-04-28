import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import * as moment from 'moment';
import {JhiAlertService} from 'ng-jhipster';
import {
    IQuittanceMensuelleImpot,
    QuittanceMensuelleImpot,
    TypeDeclaration
} from 'app/shared/model/quittance-mensuelle-impot.model';
import {QuittanceMensuelleImpotService} from './quittance-mensuelle-impot.service';
import {IFicheClient} from 'app/shared/model/fiche-client.model';
import {FicheClientService} from 'app/entities/fiche-client';
import * as _ from 'lodash';
import ComptaDecisionUtils from "app/shared/util/compta-decision-utils";

@Component({
    selector: 'jhi-quittance-mensuelle-impot-update',
    templateUrl: './quittance-mensuelle-impot-update.component.html'
})
export class QuittanceMensuelleImpotUpdateComponent implements OnInit {
    quittanceMensuelleImpot: IQuittanceMensuelleImpot;
    isSaving: boolean;
    currentYear: number;
    currentMonth: number;
    previousYears: number[];

    ficheclients: IFicheClient[];
    datePaiementDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected quittanceMensuelleImpotService: QuittanceMensuelleImpotService,
        protected ficheClientService: FicheClientService,
        protected activatedRoute: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.currentYear = moment().year();
        this.currentMonth = moment().month();
        this.activatedRoute.data.subscribe(({quittanceMensuelleImpot, ficheClients}) => {

            this.quittanceMensuelleImpot = quittanceMensuelleImpot;
            this.ficheclients = ficheClients;
            if (!this.quittanceMensuelleImpot.id) {
                if (this.ficheclients.length > 0) {
                    this.quittanceMensuelleImpot.ficheClientId = this.ficheclients[0].id;
                    this.quittanceMensuelleImpot.ficheClientDateCreation = this.ficheclients[0].dateCreation;
                    this.quittanceMensuelleImpot.ficheClientDesignation = this.ficheclients[0].designation;
                    this.quittanceMensuelleImpot.ficheClientMatriculeFiscale = this.ficheclients[0].matriculeFiscale;
                    this.quittanceMensuelleImpot.ficheClientRegistreCommerce = this.ficheclients[0].registreCommerce;
                }

                this.quittanceMensuelleImpot.typeDeclaration = TypeDeclaration.DECLARATION_INITIALE;
                this.quittanceMensuelleImpot.annee = this.currentYear;
                this.quittanceMensuelleImpot.annee = this.currentYear;
                this.quittanceMensuelleImpot.mois = this.currentMonth + 1;
                this.initByParams();
            }
                this.calculateSumMontantPaye();
            this.getAnnesDeclaration();
        });
    }

    private getAnnesDeclaration() {
        if (this.quittanceMensuelleImpot && this.quittanceMensuelleImpot.ficheClientId) {
            let ficheClientId = this.quittanceMensuelleImpot.ficheClientId;
            let ficheClient = _.find(this.ficheclients, function (ficheClient) {
                return ficheClientId === ficheClient.id;
            });
            if (ficheClient && ficheClient.dateCreation) {
                this.previousYears = ComptaDecisionUtils.getPreviousYears(moment(ficheClient.dateCreation).year(), this.currentYear);
            }
        } else {
            this.previousYears = ComptaDecisionUtils.getPreviousYears(this.currentYear -5, this.currentYear);
        }
    }

    private getSum(total, num) {
        return total + num;
    }

    /**
     * Recalculer la somme du montant de la quittance à chaque fois que le montant qu'un impot change
     */
    calculateSumMontantPaye() {
        this.quittanceMensuelleImpot.montantPaye = this.quittanceMensuelleImpot.quittanceMensuelleImpotLines
            .map(quittanceMensuelleImpotLine => quittanceMensuelleImpotLine.montantPaye).reduce(this.getSum);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.calculateSumMontantPaye();
        if (this.quittanceMensuelleImpot.id !== undefined && this.quittanceMensuelleImpot.id !== null) {
            this.subscribeToSaveResponse(this.quittanceMensuelleImpotService.update(this.quittanceMensuelleImpot));
        } else {
            this.subscribeToSaveResponse(this.quittanceMensuelleImpotService.create(this.quittanceMensuelleImpot));
        }
    }

    initByParams() {
        let anneeSelected = this.quittanceMensuelleImpot ? this.quittanceMensuelleImpot.annee : undefined;
        let moisSelected = this.quittanceMensuelleImpot ? this.quittanceMensuelleImpot.mois : undefined;
        let typeDeclarationSelected = this.quittanceMensuelleImpot ? this.quittanceMensuelleImpot.typeDeclaration : undefined;
        this.getAnnesDeclaration();
        if(this.quittanceMensuelleImpot.ficheClientId !== undefined && this.quittanceMensuelleImpot.mois !== undefined &&
            this.quittanceMensuelleImpot.ficheClientId !== null && this.quittanceMensuelleImpot.mois !== null) {
            this.quittanceMensuelleImpotService.initByParams(this.quittanceMensuelleImpot.ficheClientId, this.quittanceMensuelleImpot.mois)
                .pipe(
                    filter((response: HttpResponse<QuittanceMensuelleImpot>) => response.ok),
                    map((quittanceMensuelleImpot: HttpResponse<QuittanceMensuelleImpot>) => quittanceMensuelleImpot.body)
                )
                .subscribe((res: QuittanceMensuelleImpot) => {
                    this.quittanceMensuelleImpot = res;
                    this.quittanceMensuelleImpot.annee = anneeSelected;
                    this.quittanceMensuelleImpot.mois = moisSelected;
                    this.quittanceMensuelleImpot.typeDeclaration = typeDeclarationSelected;
                    this.calculateSumMontantPaye();
                    }, (res: HttpErrorResponse) => this.onError(res.message));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuittanceMensuelleImpot>>) {
        result.subscribe(
            (res: HttpResponse<IQuittanceMensuelleImpot>) => this.onSaveSuccess(),
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
}
