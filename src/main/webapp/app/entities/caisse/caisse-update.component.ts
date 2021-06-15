import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {JhiAlertService} from 'ng-jhipster';
import {ICaisse} from 'app/shared/model/caisse.model';
import {CaisseService} from './caisse.service';
import {IFicheClient} from 'app/shared/model/fiche-client.model';
import {FicheClientService} from 'app/entities/fiche-client';

@Component({
    selector: 'jhi-caisse-update',
    templateUrl: './caisse-update.component.html'
})
export class CaisseUpdateComponent implements OnInit {
    caisse: ICaisse;
    isSaving: boolean;

    ficheclients: IFicheClient[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected caisseService: CaisseService,
        protected ficheClientService: FicheClientService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ caisse }) => {
            this.caisse = caisse;
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
        if (this.caisse.id !== undefined) {
            this.subscribeToSaveResponse(this.caisseService.update(this.caisse));
        } else {
            this.subscribeToSaveResponse(this.caisseService.create(this.caisse));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICaisse>>) {
        result.subscribe((res: HttpResponse<ICaisse>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
