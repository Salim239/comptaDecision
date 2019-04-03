import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { IActivite } from 'app/shared/model/activite.model';
import { ActiviteService } from './activite.service';

@Component({
    selector: 'jhi-activite-update',
    templateUrl: './activite-update.component.html'
})
export class ActiviteUpdateComponent implements OnInit {
    activite: IActivite;
    isSaving: boolean;

    constructor(protected activiteService: ActiviteService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ activite }) => {
            this.activite = activite;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.activite.id !== undefined) {
            this.subscribeToSaveResponse(this.activiteService.update(this.activite));
        } else {
            this.subscribeToSaveResponse(this.activiteService.create(this.activite));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IActivite>>) {
        result.subscribe((res: HttpResponse<IActivite>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
