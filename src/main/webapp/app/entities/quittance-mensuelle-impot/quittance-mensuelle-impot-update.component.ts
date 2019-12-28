import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import * as moment from 'moment';
import {JhiAlertService} from 'ng-jhipster';
import {IQuittanceMensuelleImpot, QuittanceMensuelleImpot} from 'app/shared/model/quittance-mensuelle-impot.model';
import {QuittanceMensuelleImpotService} from './quittance-mensuelle-impot.service';
import {IFicheClient} from 'app/shared/model/fiche-client.model';
import {FicheClientService} from 'app/entities/fiche-client';
import * as _ from 'lodash';
import ComptaDecisionUtils from "app/shared/util/compta-decision-utils";
import {filter, map} from "rxjs/operators";


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

    ficheClients: IFicheClient[];
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
        this.activatedRoute.data.subscribe(({ quittanceMensuelleImpot, ficheClients }) => {
            this.quittanceMensuelleImpot = quittanceMensuelleImpot;
            this.ficheClients = ficheClients;
            this.getAnneesDeclaration();
        });
    }

    private getAnneesDeclaration() {
        if (this.quittanceMensuelleImpot && this.quittanceMensuelleImpot.ficheClientId) {
            let ficheClientId = this.quittanceMensuelleImpot.ficheClientId;
            let ficheClient = _.find(this.ficheClients, function (ficheClient) {
                return ficheClientId === ficheClient.id;
            });
            if (ficheClient && ficheClient.dateCreation) {
                this.previousYears = ComptaDecisionUtils.getPreviousYears(moment(ficheClient.dateCreation).year());
            }
        } else {
            this.previousYears = ComptaDecisionUtils.getPreviousYears(this.currentYear -5);
        }
    }


    previousState() {
        window.history.back();
    }

    save(withoutExist) {
        this.isSaving = !withoutExist;
        if (this.quittanceMensuelleImpot.id !== undefined && this.quittanceMensuelleImpot.id !== null) {
            this.subscribeToSaveResponse(this.quittanceMensuelleImpotService.update(this.quittanceMensuelleImpot), withoutExist);
        } else {
            this.subscribeToSaveResponse(this.quittanceMensuelleImpotService.create(this.quittanceMensuelleImpot), withoutExist);
        }
    }

    initByParams() {
        if(this.quittanceMensuelleImpot.ficheClientId !== undefined &&
            // this.quittanceMensuelleImpot.ficheClientId !== '' &&
            this.quittanceMensuelleImpot.ficheClientId !== null) {
            this.quittanceMensuelleImpotService.initByParams(this.quittanceMensuelleImpot.ficheClientId)
                .pipe(
                    filter((response: HttpResponse<QuittanceMensuelleImpot>) => response.ok),
                    map((quittanceMensuelleImpot: HttpResponse<QuittanceMensuelleImpot>) => quittanceMensuelleImpot.body)
                )
                .subscribe((res: QuittanceMensuelleImpot) => {
                    this.quittanceMensuelleImpot = res;
                    }, (res: HttpErrorResponse) => this.onError(res.message));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuittanceMensuelleImpot>>, withoutExist) {
        result.subscribe(
            (res: HttpResponse<IQuittanceMensuelleImpot>) => {
                if (withoutExist) {

                    this.quittanceMensuelleImpot = res.body;
                } else {
                    this.onSaveSuccess()
                }
            },
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


    protected calculerMontantTotalDetail(quittanceMensuelleImpotDetail) {

        return _.sum(_.map(quittanceMensuelleImpotDetail.quittanceMensuelleImpotSousDetails, function (quittanceMensuelleImpotSousDetail) {
            return quittanceMensuelleImpotSousDetail.montantTotal;
        }));
    }

    protected calculerMontantTotalDetailWithChildren(quittanceMensuelleImpotDetail) {

        console.log("report ", quittanceMensuelleImpotDetail.montantTotalReport);
        return _.sum(_.map(quittanceMensuelleImpotDetail.childQuittanceMensuelleImpotDetails, function (child) {
            return child.montantTotal * child.impotMensuelCoefficientMontant;
        })) - quittanceMensuelleImpotDetail.montantTotalReport;
    }


    updateMontantTotal(quittanceMensuelleImpotDetail) {

        quittanceMensuelleImpotDetail.montantTotal = this.calculerMontantTotalDetail(quittanceMensuelleImpotDetail);

        if (quittanceMensuelleImpotDetail.parentQuittanceMensuelleImpotDetailCode) {
            console.log("parentQuittanceMensuelleImpotDetailCode ", quittanceMensuelleImpotDetail.parentQuittanceMensuelleImpotDetailCode);
            let parentDetail = _.find(this.quittanceMensuelleImpot.quittanceMensuelleImpotDetails, function (detail) {
                return detail.code === quittanceMensuelleImpotDetail.parentQuittanceMensuelleImpotDetailCode;
            });
            parentDetail.montantTotal = this.calculerMontantTotalDetailWithChildren(parentDetail);
        }

        this.quittanceMensuelleImpot.montantTotal = _.sum(_.map(this.quittanceMensuelleImpot.quittanceMensuelleImpotDetails, function (detail) {
            return detail.montantTotal;
        }));
    }
}
