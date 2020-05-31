import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ICentreAdministratif } from 'app/shared/model/centre-administratif.model';
import { CentreAdministratifService } from './centre-administratif.service';
import { IRegion } from 'app/shared/model/region.model';
import { IVille } from 'app/shared/model/ville.model';
import { RegionService } from 'app/entities/region';
import { VilleService } from 'app/entities/ville';
import { JhiAlertService } from 'ng-jhipster';

@Component({
    selector: 'jhi-centre-administratif-update',
    templateUrl: './centre-administratif-update.component.html'
})
export class CentreAdministratifUpdateComponent implements OnInit {
    centreAdministratif: ICentreAdministratif;
    isSaving: boolean;
    regions: IRegion[];
    villes: IVille[];

    constructor(
        protected centreAdministratifService: CentreAdministratifService,
        protected activatedRoute: ActivatedRoute,
        protected jhiAlertService: JhiAlertService,
        protected regionService: RegionService,
        protected villeService: VilleService
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ centreAdministratif }) => {
            this.centreAdministratif = centreAdministratif;
        });
        this.regionService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IRegion[]>) => mayBeOk.ok),
                map((response: HttpResponse<IRegion[]>) => response.body)
            )
            .subscribe((res: IRegion[]) => (this.regions = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.villeService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IVille[]>) => mayBeOk.ok),
                map((response: HttpResponse<IVille[]>) => response.body)
            )
            .subscribe((res: IVille[]) => (this.villes = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.centreAdministratif.id !== undefined) {
            this.subscribeToSaveResponse(this.centreAdministratifService.update(this.centreAdministratif));
        } else {
            this.subscribeToSaveResponse(this.centreAdministratifService.create(this.centreAdministratif));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICentreAdministratif>>) {
        result.subscribe((res: HttpResponse<ICentreAdministratif>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackRegionById(index: number, item: IRegion) {
        return item.id;
    }

    trackVilleById(index: number, item: IVille) {
        return item.id;
    }
}
