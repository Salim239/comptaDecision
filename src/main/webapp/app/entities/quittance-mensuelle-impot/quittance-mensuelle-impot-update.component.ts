import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IQuittanceMensuelleImpot, QuittanceMensuelleImpot } from 'app/shared/model/quittance-mensuelle-impot.model';
import { QuittanceMensuelleImpotService } from './quittance-mensuelle-impot.service';
import { IFicheClient } from 'app/shared/model/fiche-client.model';
import { FicheClientService } from 'app/entities/fiche-client';
import * as _ from 'lodash';
import { filter, map } from 'rxjs/operators';

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
        protected activatedRoute: ActivatedRoute,
        protected router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ quittanceMensuelleImpot, ficheClients }) => {
            this.quittanceMensuelleImpot = this.quittanceMensuelleImpotService.formatMontants(quittanceMensuelleImpot);
            this.ficheClients = ficheClients;
        });
    }

    previousState() {
        this.router.navigateByUrl('/quittance-mensuelle-impot');
    }

    save(withoutExist) {
        this.isSaving = !withoutExist;
        this.quittanceMensuelleImpot = this.quittanceMensuelleImpotService.parseMontants(this.quittanceMensuelleImpot);
        if (this.quittanceMensuelleImpot.id !== undefined && this.quittanceMensuelleImpot.id !== null) {
            this.subscribeToSaveResponse(this.quittanceMensuelleImpotService.update(this.quittanceMensuelleImpot), withoutExist);
        } else {
            this.subscribeToSaveResponse(this.quittanceMensuelleImpotService.create(this.quittanceMensuelleImpot), withoutExist);
        }
    }

    initByParams() {
        if (
            this.quittanceMensuelleImpot.ficheClientId !== undefined &&
            // this.quittanceMensuelleImpot.ficheClientId !== '' &&
            this.quittanceMensuelleImpot.ficheClientId !== null
        ) {
            this.quittanceMensuelleImpotService
                .initByParams(this.quittanceMensuelleImpot.ficheClientId)
                .pipe(
                    filter((response: HttpResponse<QuittanceMensuelleImpot>) => response.ok),
                    map((quittanceMensuelleImpot: HttpResponse<QuittanceMensuelleImpot>) => quittanceMensuelleImpot.body)
                )
                .subscribe(
                    (res: QuittanceMensuelleImpot) => {
                        this.quittanceMensuelleImpot = res;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuittanceMensuelleImpot>>, withoutExist) {
        result.subscribe(
            (res: HttpResponse<IQuittanceMensuelleImpot>) => {
                if (withoutExist) {
                    this.quittanceMensuelleImpot = _.cloneDeep(res.body);
                    this.quittanceMensuelleImpot = this.quittanceMensuelleImpotService.formatMontants(this.quittanceMensuelleImpot);
                } else {
                    this.onSaveSuccess();
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

    calculerMontants(event) {
        this.quittanceMensuelleImpotService.save(this.quittanceMensuelleImpot).subscribe((res: IQuittanceMensuelleImpot) => {
            this.quittanceMensuelleImpotService.updateQuittanceModel(this.quittanceMensuelleImpot, res);
            console.log('quittanceMensuelleImpot ', this.quittanceMensuelleImpot);
        });
    }
}
