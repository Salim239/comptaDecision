import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {JhiAlertService} from 'ng-jhipster';
import {IImpotMensuelClient} from 'app/shared/model/impot-mensuel-client.model';
import {ImpotMensuelClientService} from './impot-mensuel-client.service';
import {IFicheClient} from 'app/shared/model/fiche-client.model';
import {FicheClientService} from 'app/entities/fiche-client';
import {IImpotMensuel} from 'app/shared/model/impot-mensuel.model';
import {ImpotMensuelService} from 'app/entities/impot-mensuel';
import {IQuittanceMensuelleImpotDetail} from 'app/shared/model/quittance-mensuelle-impot-detail.model';
import {QuittanceMensuelleImpotDetailService} from 'app/entities/quittance-mensuelle-impot-detail';

@Component({
    selector: 'jhi-impot-mensuel-client-update',
    templateUrl: './impot-mensuel-client-update.component.html'
})
export class ImpotMensuelClientUpdateComponent implements OnInit {
    impotMensuelClient: IImpotMensuelClient;
    isSaving: boolean;

    ficheclients: IFicheClient[];

    impotmensuels: IImpotMensuel[];

    quittancemensuelleimpotlines: IQuittanceMensuelleImpotDetail[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected impotMensuelClientService: ImpotMensuelClientService,
        protected ficheClientService: FicheClientService,
        protected impotMensuelService: ImpotMensuelService,
        protected quittanceMensuelleImpotDetailService: QuittanceMensuelleImpotDetailService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ impotMensuelClient }) => {
            this.impotMensuelClient = impotMensuelClient;
        });
        this.ficheClientService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IFicheClient[]>) => mayBeOk.ok),
                map((response: HttpResponse<IFicheClient[]>) => response.body)
            )
            .subscribe((res: IFicheClient[]) => (this.ficheclients = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.impotMensuelService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IImpotMensuel[]>) => mayBeOk.ok),
                map((response: HttpResponse<IImpotMensuel[]>) => response.body)
            )
            .subscribe((res: IImpotMensuel[]) => (this.impotmensuels = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.quittanceMensuelleImpotDetailService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IQuittanceMensuelleImpotDetail[]>) => mayBeOk.ok),
                map((response: HttpResponse<IQuittanceMensuelleImpotDetail[]>) => response.body)
            )
            .subscribe(
                (res: IQuittanceMensuelleImpotDetail[]) => (this.quittancemensuelleimpotlines = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.impotMensuelClient.id !== undefined) {
            this.subscribeToSaveResponse(this.impotMensuelClientService.update(this.impotMensuelClient));
        } else {
            this.subscribeToSaveResponse(this.impotMensuelClientService.create(this.impotMensuelClient));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IImpotMensuelClient>>) {
        result.subscribe((res: HttpResponse<IImpotMensuelClient>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackImpotMensuelById(index: number, item: IImpotMensuel) {
        return item.id;
    }

    trackQuittanceMensuelleImpotDetailById(index: number, item: IQuittanceMensuelleImpotDetail) {
        return item.id;
    }
}
