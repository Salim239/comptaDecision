import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IQuittanceMensuelleLigne } from 'app/shared/model/quittance-mensuelle-ligne.model';
import { QuittanceMensuelleLigneService } from './quittance-mensuelle-ligne.service';
import { IQuittanceMensuelle } from 'app/shared/model/quittance-mensuelle.model';
import { QuittanceMensuelleService } from 'app/entities/quittance-mensuelle';

@Component({
    selector: 'jhi-quittance-mensuelle-ligne-update',
    templateUrl: './quittance-mensuelle-ligne-update.component.html'
})
export class QuittanceMensuelleLigneUpdateComponent implements OnInit {
    quittanceMensuelleLigne: IQuittanceMensuelleLigne;
    isSaving: boolean;

    quittanceMensuelles: IQuittanceMensuelle[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected quittanceMensuelleLigneService: QuittanceMensuelleLigneService,
        protected quittanceMensuelleService: QuittanceMensuelleService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ quittanceMensuelleLigne }) => {
            this.quittanceMensuelleLigne = quittanceMensuelleLigne;
        });
        this.quittanceMensuelleService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IQuittanceMensuelle[]>) => mayBeOk.ok),
                map((response: HttpResponse<IQuittanceMensuelle[]>) => response.body)
            )
            .subscribe(
                (res: IQuittanceMensuelle[]) => (this.quittanceMensuelles = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.quittanceMensuelleLigne.id !== undefined) {
            this.subscribeToSaveResponse(this.quittanceMensuelleLigneService.update(this.quittanceMensuelleLigne));
        } else {
            this.subscribeToSaveResponse(this.quittanceMensuelleLigneService.create(this.quittanceMensuelleLigne));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IQuittanceMensuelleLigne>>) {
        result.subscribe(
            (res: HttpResponse<IQuittanceMensuelleLigne>) => this.onSaveSuccess(),
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

    trackQuittanceMensuelleById(index: number, item: IQuittanceMensuelle) {
        return item.id;
    }
}
