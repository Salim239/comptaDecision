import { Component, ElementRef, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { FicheClient, IFicheClient } from 'app/shared/model/fiche-client.model';
import { FicheClientService } from './fiche-client.service';
import { ISecteurActivite } from 'app/shared/model/secteur-activite.model';
import { SecteurActiviteService } from 'app/entities/secteur-activite';
import { IActivite } from 'app/shared/model/activite.model';
import { ActiviteService } from 'app/entities/activite';
import { IRegion } from 'app/shared/model/region.model';
import { RegionService } from 'app/entities/region';
import { IVille } from 'app/shared/model/ville.model';
import { VilleService } from 'app/entities/ville';
import { CentreAdministratifService } from 'app/entities/centre-administratif';
import { ICentreAdministratif, TypeCentreAdministratif } from 'app/shared/model/centre-administratif.model';
import ComptaDecisionUtils from 'app/shared/util/compta-decision-utils';

@Component({
    selector: 'jhi-fiche-client-update',
    templateUrl: './fiche-client-update.component.html'
})
export class FicheClientUpdateComponent implements OnInit {
    ficheClient: IFicheClient;
    isSaving: boolean;
    villes$: Observable<IVille[]>;
    activites1$: Observable<IActivite[]>;
    activites2$: Observable<IActivite[]>;
    activites3$: Observable<IActivite[]>;
    secteuractivites$: Observable<ISecteurActivite[]>;
    centreAdministratifCnsss$: Observable<ICentreAdministratif[]>;
    centreAdministratifFiscales$: Observable<ICentreAdministratif[]>;
    centreAdministratifImpots$: Observable<ICentreAdministratif[]>;
    regions$: Observable<IRegion[]>;

    dateCreationDp: any;

    constructor(
        protected dataUtils: JhiDataUtils,
        protected jhiAlertService: JhiAlertService,
        protected ficheClientService: FicheClientService,
        protected centreAdministratifService: CentreAdministratifService,
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
            this.formatDecimalFields();
        });
        this.regions$ = this.findRegions();
        this.secteuractivites$ = this.findSecteursActivites();
        this.activites1$ = this.findActivites();
        this.activites2$ = this.findActivites();
        this.activites3$ = this.findActivites();
        this.villes$ = this.findVilleByRegionId();
        this.centreAdministratifCnsss$ = this.findAdministrationsCnss();
        this.centreAdministratifFiscales$ = this.findAdministrationsFiscale();
        this.centreAdministratifImpots$ = this.findAdministrationsImpot();
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
        this.parseDecimalFields();
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

    findVilleByRegionId(regionId?: number) {
        return regionId
            ? this.villeService.findByRegionId(regionId).pipe(
                  filter((response: HttpResponse<IVille[]>) => response.ok),
                  map((response: HttpResponse<IVille[]>) => response.body)
              )
            : this.villeService.query().pipe(
                  filter((response: HttpResponse<IVille[]>) => response.ok),
                  map((response: HttpResponse<IVille[]>) => response.body)
              );
    }

    findVille() {
        this.villes$ = undefined;
        this.ficheClient.villeId = undefined;
        this.ficheClient.villeLibelle = undefined;
        this.villes$ = this.findVilleByRegionId(this.ficheClient.regionId);
    }

    findRegions() {
        return this.regionService.query().pipe(
            filter((response: HttpResponse<IRegion[]>) => response.ok),
            map((response: HttpResponse<IRegion[]>) => response.body)
        );
    }

    findActivites(secteurActiviteId?: number) {
        return secteurActiviteId
            ? this.activiteService.findBySecteurActiviteId(secteurActiviteId).pipe(
                  filter((response: HttpResponse<IActivite[]>) => response.ok),
                  map((response: HttpResponse<IActivite[]>) => response.body)
              )
            : this.activiteService.query().pipe(
                  filter((response: HttpResponse<IActivite[]>) => response.ok),
                  map((response: HttpResponse<IActivite[]>) => {
                      const data = response.body;
                      return data;
                  })
              );
    }

    findActivites1() {
        this.activites1$ = undefined;
        this.ficheClient.activite1Id = undefined;
        this.ficheClient.activite1Libelle = undefined;
        this.activites1$ = this.findActivites(this.ficheClient.secteurActivite1Id);
    }

    findActivites2() {
        this.activites2$ = undefined;
        this.ficheClient.activite2Id = undefined;
        this.ficheClient.activite2Libelle = undefined;
        this.activites2$ = this.findActivites(this.ficheClient.secteurActivite2Id);
    }

    findActivites3() {
        this.activites3$ = undefined;
        this.ficheClient.activite3Id = undefined;
        this.ficheClient.activite3Libelle = undefined;
        this.activites3$ = this.findActivites(this.ficheClient.secteurActivite3Id);
    }

    findSecteursActivites() {
        return this.secteurActiviteService.query().pipe(
            filter((response: HttpResponse<ISecteurActivite[]>) => response.ok),
            map((response: HttpResponse<ISecteurActivite[]>) => {
                const data = response.body;
                return data;
            })
        );
    }

    findAdministrationsCnss() {
        return this.centreAdministratifService.findByType(TypeCentreAdministratif.administration_cnss).pipe(
            filter((response: HttpResponse<ICentreAdministratif[]>) => response.ok),
            map((response: HttpResponse<ICentreAdministratif[]>) => {
                const data = response.body;
                return data;
            })
        );
    }

    findAdministrationsImpot() {
        return this.centreAdministratifService.findByType(TypeCentreAdministratif.administration_impot).pipe(
            filter((response: HttpResponse<ICentreAdministratif[]>) => response.ok),
            map((response: HttpResponse<ICentreAdministratif[]>) => {
                const data = response.body;
                return data;
            })
        );
    }

    findAdministrationsFiscale() {
        return this.centreAdministratifService.findByType(TypeCentreAdministratif.administration_fiscale).pipe(
            filter((response: HttpResponse<ICentreAdministratif[]>) => response.ok),
            map((response: HttpResponse<ICentreAdministratif[]>) => {
                const data = response.body;
                return data;
            })
        );
    }

    formatDecimalFields() {
        if (this.ficheClient.tauxCnssAccident) {
            this.ficheClient.tauxCnssAccident = ComptaDecisionUtils.parseCurrency(this.ficheClient.tauxCnssAccident);
        }
    }

    parseDecimalFields() {
        if (this.ficheClient.tauxCnssAccident) {
            this.ficheClient.tauxCnssAccident = ComptaDecisionUtils.parseCurrency(this.ficheClient.tauxCnssAccident);
        }
    }
}
