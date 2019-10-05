import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {JhiAlertService} from 'ng-jhipster';
import {IQuittanceMensuelleImpotDetail} from 'app/shared/model/quittance-mensuelle-impot-detail.model';
import {QuittanceMensuelleImpotDetailService} from './quittance-mensuelle-impot-detail.service';
import {IQuittanceMensuelleImpot} from 'app/shared/model/quittance-mensuelle-impot.model';
import {QuittanceMensuelleImpotService} from 'app/entities/quittance-mensuelle-impot';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-detail-update',
    templateUrl: './quittance-mensuelle-impot-detail-update.component.html'
})
export class QuittanceMensuelleImpotDetailUpdateComponent implements OnInit {
    quittanceMensuelleImpotDetail: IQuittanceMensuelleImpotDetail;
    isSaving: boolean;

    quittancemensuelleimpots: IQuittanceMensuelleImpot[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected quittanceMensuelleImpotDetailService: QuittanceMensuelleImpotDetailService,
        protected quittanceMensuelleImpotService: QuittanceMensuelleImpotService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ quittanceMensuelleImpotDetail }) => {
            this.quittanceMensuelleImpotDetail = quittanceMensuelleImpotDetail;
        });
        this.quittanceMensuelleImpotService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IQuittanceMensuelleImpot[]>) => mayBeOk.ok),
                map((response: HttpResponse<IQuittanceMensuelleImpot[]>) => response.body)
            )
            .subscribe(
                (res: IQuittanceMensuelleImpot[]) => (this.quittancemensuelleimpots = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.quittanceMensuelleImpotDetail.id !== undefined) {
            this.subscribeToSaveResponse(this.quittanceMensuelleImpotDetailService.update(this.quittanceMensuelleImpotDetail));
        } else {
            this.subscribeToSaveResponse(this.quittanceMensuelleImpotDetailService.create(this.quittanceMensuelleImpotDetail));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuittanceMensuelleImpotDetail>>) {
        result.subscribe(
            (res: HttpResponse<IQuittanceMensuelleImpotDetail>) => this.onSaveSuccess(),
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

    trackQuittanceMensuelleImpotById(index: number, item: IQuittanceMensuelleImpot) {
        return item.id;
    }
}
