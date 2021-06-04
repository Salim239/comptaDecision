import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';
import { IQuittanceMensuelle, QuittanceMensuelle } from 'app/shared/model/quittance-mensuelle.model';
import { QuittanceMensuelleService } from './quittance-mensuelle.service';
import { IFicheClient } from 'app/shared/model/fiche-client.model';
import { FicheClientService } from 'app/entities/fiche-client';
import * as _ from 'lodash';
import { filter, map } from 'rxjs/operators';

@Component({
    selector: 'jhi-quittance-mensuelle-update',
    templateUrl: './quittance-mensuelle-update.component.html'
})
export class QuittanceMensuelleUpdateComponent implements OnInit {
    quittanceMensuelle: IQuittanceMensuelle;
    isSaving: boolean;
    currentYear: number;
    currentMonth: number;
    previousYears: number[];
    ficheClients: IFicheClient[];
    datePaiementDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected quittanceMensuelleService: QuittanceMensuelleService,
        protected ficheClientService: FicheClientService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ quittanceMensuelle, ficheClients }) => {
            this.quittanceMensuelle = this.quittanceMensuelleService.formatMontants(quittanceMensuelle);
            this.ficheClients = ficheClients;
        });
    }

    previousState() {
        this.router.navigateByUrl('/quittance-mensuelle');
    }

    save(withoutExist) {
        this.isSaving = !withoutExist;
        this.quittanceMensuelle = this.quittanceMensuelleService.parseMontants(this.quittanceMensuelle);
        if (this.quittanceMensuelle.id !== undefined && this.quittanceMensuelle.id !== null) {
            this.subscribeToSaveResponse(this.quittanceMensuelleService.update(this.quittanceMensuelle), withoutExist);
        } else {
            this.subscribeToSaveResponse(this.quittanceMensuelleService.create(this.quittanceMensuelle), withoutExist);
        }
    }

    initByParams() {
        if (
            this.quittanceMensuelle.ficheClientId !== undefined &&
            // this.quittanceMensuelle.ficheClientId !== '' &&
            this.quittanceMensuelle.ficheClientId !== null
        ) {
            this.quittanceMensuelleService
                .initByParams(this.quittanceMensuelle.ficheClientId)
                .pipe(
                    filter((response: HttpResponse<QuittanceMensuelle>) => response.ok),
                    map((quittanceMensuelle: HttpResponse<QuittanceMensuelle>) => quittanceMensuelle.body)
                )
                .subscribe(
                    (res: QuittanceMensuelle) => {
                        this.quittanceMensuelle = res;
                    },
                    (res: HttpErrorResponse) => this.onError(res.message)
                );
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuittanceMensuelle>>, withoutExist) {
        result.subscribe(
            (res: HttpResponse<IQuittanceMensuelle>) => {
                if (withoutExist) {
                    this.quittanceMensuelle = _.cloneDeep(res.body);
                    this.quittanceMensuelle = this.quittanceMensuelleService.formatMontants(this.quittanceMensuelle);
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
        this.quittanceMensuelleService.save(this.quittanceMensuelle).subscribe((res: IQuittanceMensuelle) => {
            this.quittanceMensuelleService.updateQuittanceModel(this.quittanceMensuelle, res);
            console.log('quittanceMensuelle ', this.quittanceMensuelle);
        });
    }
}
