import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IDeclarationEmployeurAnnuelle } from 'app/shared/model/declaration-employeur-annuelle.model';
import { DeclarationEmployeurAnnuelleService } from './declaration-employeur-annuelle.service';
import { IFicheClient } from 'app/shared/model/fiche-client.model';
import { FicheClientService } from 'app/entities/fiche-client';

@Component({
    selector: 'jhi-declaration-employeur-annuelle-update',
    templateUrl: './declaration-employeur-annuelle-update.component.html'
})
export class DeclarationEmployeurAnnuelleUpdateComponent implements OnInit {
    declarationEmployeurAnnuelle: IDeclarationEmployeurAnnuelle;
    isSaving: boolean;

    ficheclients: IFicheClient[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected declarationEmployeurAnnuelleService: DeclarationEmployeurAnnuelleService,
        protected ficheClientService: FicheClientService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ declarationEmployeurAnnuelle }) => {
            this.declarationEmployeurAnnuelle = declarationEmployeurAnnuelle;
        });
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
        if (this.declarationEmployeurAnnuelle.id !== undefined) {
            this.subscribeToSaveResponse(this.declarationEmployeurAnnuelleService.update(this.declarationEmployeurAnnuelle));
        } else {
            this.subscribeToSaveResponse(this.declarationEmployeurAnnuelleService.create(this.declarationEmployeurAnnuelle));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeclarationEmployeurAnnuelle>>) {
        result.subscribe(
            (res: HttpResponse<IDeclarationEmployeurAnnuelle>) => this.onSaveSuccess(),
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

    trackFicheClientById(index: number, item: IFicheClient) {
        return item.id;
    }
}
