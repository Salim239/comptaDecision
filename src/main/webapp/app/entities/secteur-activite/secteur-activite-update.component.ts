import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ISecteurActivite } from 'app/shared/model/secteur-activite.model';
import { SecteurActiviteService } from './secteur-activite.service';

@Component({
    selector: 'jhi-secteur-activite-update',
    templateUrl: './secteur-activite-update.component.html'
})
export class SecteurActiviteUpdateComponent implements OnInit {
    secteurActivite: ISecteurActivite;
    isSaving: boolean;

    constructor(protected secteurActiviteService: SecteurActiviteService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ secteurActivite }) => {
            this.secteurActivite = secteurActivite;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.secteurActivite.id !== undefined) {
            this.subscribeToSaveResponse(this.secteurActiviteService.update(this.secteurActivite));
        } else {
            this.subscribeToSaveResponse(this.secteurActiviteService.create(this.secteurActivite));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ISecteurActivite>>) {
        result.subscribe((res: HttpResponse<ISecteurActivite>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
