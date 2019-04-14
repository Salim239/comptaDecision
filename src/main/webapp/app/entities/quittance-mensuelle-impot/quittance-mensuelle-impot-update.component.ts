import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpResponse, HttpErrorResponse} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import * as moment from 'moment';
import {JhiAlertService} from 'ng-jhipster';
import {IQuittanceMensuelleImpot, QuittanceMensuelleImpot} from 'app/shared/model/quittance-mensuelle-impot.model';
import {QuittanceMensuelleImpotService} from './quittance-mensuelle-impot.service';
import {IFicheClient} from 'app/shared/model/fiche-client.model';
import {FicheClientService} from 'app/entities/fiche-client';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-update',
    templateUrl: './quittance-mensuelle-impot-update.component.html'
})
export class QuittanceMensuelleImpotUpdateComponent implements OnInit {
    quittanceMensuelleImpot: IQuittanceMensuelleImpot;
    isSaving: boolean;

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
        this.activatedRoute.data.subscribe(({quittanceMensuelleImpot}) => {
            this.quittanceMensuelleImpot = quittanceMensuelleImpot;
        });
        this.ficheClientService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IFicheClient[]>) => mayBeOk.ok),
                map((response: HttpResponse<IFicheClient[]>) => response.body)
            )
            .subscribe((res: IFicheClient[]) => {
                this.ficheclients = res;
                this.calculateSumMontantPaye();
                }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    getSum(total, num) {
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
        if(this.quittanceMensuelleImpot.ficheClientId !== undefined && this.quittanceMensuelleImpot.mois !== undefined &&
            this.quittanceMensuelleImpot.ficheClientId !== null && this.quittanceMensuelleImpot.mois !== null) {
            this.quittanceMensuelleImpotService.initByParams(this.quittanceMensuelleImpot.ficheClientId, this.quittanceMensuelleImpot.mois)
                .pipe(
                    filter((response: HttpResponse<QuittanceMensuelleImpot>) => response.ok),
                    map((quittanceMensuelleImpot: HttpResponse<QuittanceMensuelleImpot>) => quittanceMensuelleImpot.body)
                )
                .subscribe((res: QuittanceMensuelleImpot) => (this.quittanceMensuelleImpot = res), (res: HttpErrorResponse) => this.onError(res.message));
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
