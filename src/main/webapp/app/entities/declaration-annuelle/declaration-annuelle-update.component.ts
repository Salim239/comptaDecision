import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import * as moment from 'moment';
import {JhiAlertService} from 'ng-jhipster';
import {IDeclarationAnnuelle, TypeDeclaration} from 'app/shared/model/declaration-annuelle.model';
import {DeclarationAnnuelleService} from './declaration-annuelle.service';
import {IFicheClient} from 'app/shared/model/fiche-client.model';
import {FicheClientService} from 'app/entities/fiche-client';

@Component({
    selector: 'jhi-declaration-annuelle-update',
    templateUrl: './declaration-annuelle-update.component.html'
})
export class DeclarationAnnuelleUpdateComponent implements OnInit {
    declarationAnnuelle: IDeclarationAnnuelle;
    isSaving: boolean;

    ficheclients: IFicheClient[];
    datePaiementDp: any;
    currentYear: number;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected declarationAnnuelleService: DeclarationAnnuelleService,
        protected ficheClientService: FicheClientService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.currentYear = moment().year();
        this.activatedRoute.data.subscribe(({ declarationAnnuelle, ficheClients }) => {
            this.ficheclients = ficheClients;
            this.declarationAnnuelle = declarationAnnuelle;
            if (!this.declarationAnnuelle.id) {
                if (this.ficheclients.length > 0) {
                    this.declarationAnnuelle.typeDeclaration = TypeDeclaration.DECLARATION_INITIALE;
                    this.declarationAnnuelle.ficheClientId = this.ficheclients[0].id;
                    this.declarationAnnuelle.ficheClientDateCreation = this.ficheclients[0].dateCreation;
                    this.declarationAnnuelle.ficheClientDesignation = this.ficheclients[0].designation;
                    this.declarationAnnuelle.ficheClientMatriculeFiscale = this.ficheclients[0].matriculeFiscale;
                    this.declarationAnnuelle.ficheClientRegistreCommerce = this.ficheclients[0].registreCommerce;
                }
                this.declarationAnnuelle.annee = this.currentYear;
            }
        });
        this.currentYear = moment().year();
        this.ficheClientService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IFicheClient[]>) => mayBeOk.ok),
                map((response: HttpResponse<IFicheClient[]>) => response.body)
            )
            .subscribe((res: IFicheClient[]) => (this.ficheclients = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.declarationAnnuelle.id !== undefined) {
            this.subscribeToSaveResponse(this.declarationAnnuelleService.update(this.declarationAnnuelle));
        } else {
            this.subscribeToSaveResponse(this.declarationAnnuelleService.create(this.declarationAnnuelle));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeclarationAnnuelle>>) {
        result.subscribe((res: HttpResponse<IDeclarationAnnuelle>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
}
