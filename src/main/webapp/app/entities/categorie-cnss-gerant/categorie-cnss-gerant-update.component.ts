import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ICategorieCnssGerant } from 'app/shared/model/categorie-cnss-gerant.model';
import { CategorieCnssGerantService } from './categorie-cnss-gerant.service';

@Component({
    selector: 'jhi-secteur-activite-update',
    templateUrl: './categorie-cnss-gerant-update.component.html'
})
export class CategorieCnssGerantUpdateComponent implements OnInit {
    categorieCnssGerant: ICategorieCnssGerant;
    isSaving: boolean;

    constructor(protected categorieCnssGerantService: CategorieCnssGerantService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ categorieCnssGerant }) => {
            this.categorieCnssGerant = categorieCnssGerant;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.categorieCnssGerant.id !== undefined) {
            this.subscribeToSaveResponse(this.categorieCnssGerantService.update(this.categorieCnssGerant));
        } else {
            this.subscribeToSaveResponse(this.categorieCnssGerantService.create(this.categorieCnssGerant));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategorieCnssGerant>>) {
        result.subscribe((res: HttpResponse<ICategorieCnssGerant>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
