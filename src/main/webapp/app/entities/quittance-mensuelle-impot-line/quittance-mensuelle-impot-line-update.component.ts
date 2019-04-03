import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IQuittanceMensuelleImpotLine } from 'app/shared/model/quittance-mensuelle-impot-line.model';
import { QuittanceMensuelleImpotLineService } from './quittance-mensuelle-impot-line.service';
import { IQuittanceMensuelleImpot } from 'app/shared/model/quittance-mensuelle-impot.model';
import { QuittanceMensuelleImpotService } from 'app/entities/quittance-mensuelle-impot';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-line-update',
    templateUrl: './quittance-mensuelle-impot-line-update.component.html'
})
export class QuittanceMensuelleImpotLineUpdateComponent implements OnInit {
    quittanceMensuelleImpotLine: IQuittanceMensuelleImpotLine;
    isSaving: boolean;

    quittancemensuelleimpots: IQuittanceMensuelleImpot[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected quittanceMensuelleImpotLineService: QuittanceMensuelleImpotLineService,
        protected quittanceMensuelleImpotService: QuittanceMensuelleImpotService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ quittanceMensuelleImpotLine }) => {
            this.quittanceMensuelleImpotLine = quittanceMensuelleImpotLine;
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
        if (this.quittanceMensuelleImpotLine.id !== undefined) {
            this.subscribeToSaveResponse(this.quittanceMensuelleImpotLineService.update(this.quittanceMensuelleImpotLine));
        } else {
            this.subscribeToSaveResponse(this.quittanceMensuelleImpotLineService.create(this.quittanceMensuelleImpotLine));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuittanceMensuelleImpotLine>>) {
        result.subscribe(
            (res: HttpResponse<IQuittanceMensuelleImpotLine>) => this.onSaveSuccess(),
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
