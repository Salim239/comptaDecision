import {Component, ElementRef, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {JhiAlertService, JhiDataUtils} from 'ng-jhipster';
import {IFicheClient} from 'app/shared/model/fiche-client.model';
import {FicheClientService} from './fiche-client.service';
import {ISecteurActivite} from 'app/shared/model/secteur-activite.model';
import {SecteurActiviteService} from 'app/entities/secteur-activite';
import {IActivite} from 'app/shared/model/activite.model';
import {ActiviteService} from 'app/entities/activite';
import {IRegion} from 'app/shared/model/region.model';
import {RegionService} from 'app/entities/region';
import {IVille} from 'app/shared/model/ville.model';
import {VilleService} from 'app/entities/ville';

@Component({
    selector: 'jhi-fiche-client-update',
    templateUrl: './fiche-client-update.component.html'
})
export class FicheClientUpdateComponent implements OnInit {
    ficheClient: IFicheClient;
    isSaving: boolean;

    secteuractivites: ISecteurActivite[];

    activites: IActivite[];

    regions: IRegion[];

    villes: IVille[];
    dateCreationDp: any;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected ficheClientService: FicheClientService,
        protected secteurActiviteService: SecteurActiviteService,
        protected activiteService: ActiviteService,
        protected regionService: RegionService,
        protected villeService: VilleService,
        protected elementRef: ElementRef,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ficheClient }) => {
            this.ficheClient = ficheClient;
        });
        this.secteurActiviteService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ISecteurActivite[]>) => mayBeOk.ok),
                map((response: HttpResponse<ISecteurActivite[]>) => response.body)
            )
            .subscribe((res: ISecteurActivite[]) => (this.secteuractivites = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.activiteService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IActivite[]>) => mayBeOk.ok),
                map((response: HttpResponse<IActivite[]>) => response.body)
            )
            .subscribe((res: IActivite[]) => (this.activites = res), (res: HttpErrorResponse) => this.onError(res.message));
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

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.ficheClient, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.ficheClient.id !== undefined && this.ficheClient.id !== null) {
            this.subscribeToSaveResponse(this.ficheClientService.update(this.ficheClient));
        } else {
            this.subscribeToSaveResponse(this.ficheClientService.create(this.ficheClient));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IFicheClient>>) {
        result.subscribe((res: HttpResponse<IFicheClient>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackSecteurActiviteById(index: number, item: ISecteurActivite) {
        return item.id;
    }

    trackActiviteById(index: number, item: IActivite) {
        return item.id;
    }

    trackRegionById(index: number, item: IRegion) {
        return item.id;
    }

    trackVilleById(index: number, item: IVille) {
        return item.id;
    }
}
