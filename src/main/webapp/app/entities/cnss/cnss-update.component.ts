import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import * as moment from 'moment';
import {JhiAlertService} from 'ng-jhipster';
import {ICnss, TypeCnss} from 'app/shared/model/cnss.model';
import {CnssService} from './cnss.service';
import {IFicheClient} from 'app/shared/model/fiche-client.model';
import {FicheClientService} from 'app/entities/fiche-client';

@Component({
    selector: 'jhi-cnss-update',
    templateUrl: './cnss-update.component.html'
})
export class CnssUpdateComponent implements OnInit {
    cnss: ICnss;
    isSaving: boolean;
    currentYear: number;
    ficheclients: IFicheClient[];
    trimestres:any[];
    dateDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected cnssService: CnssService,
        protected ficheClientService: FicheClientService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.currentYear = moment().year();
        this.activatedRoute.data.subscribe(({ cnss, ficheClients }) => {
            this.cnss = cnss;
            this.ficheclients = ficheClients;
            if (!this.cnss.id) {
                if (this.ficheclients.length > 0) {
                    this.cnss.typeCnss = TypeCnss.CNSS_GENERALE;
                    this.cnss.ficheClientId = this.ficheclients[0].id;
                    this.cnss.ficheClientDateCreation = this.ficheclients[0].dateCreation;
                    this.cnss.ficheClientDesignation = this.ficheclients[0].designation;
                    this.cnss.ficheClientMatriculeFiscale = this.ficheclients[0].matriculeFiscale;
                    this.cnss.ficheClientRegistreCommerce = this.ficheclients[0].registreCommerce;
                }
                this.cnss.annee = this.currentYear;
            }
        });
        this.trimestres = [
            {id: 1, libelle: 'Trimestre 1'},
            {id: 2, libelle: 'Trimestre 2'},
            {id: 3, libelle: 'Trimestre 3'},
            {id: 4, libelle: 'Trimestre 4'}
        ];
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
        if (this.cnss.id !== undefined) {
            this.subscribeToSaveResponse(this.cnssService.update(this.cnss));
        } else {
            this.subscribeToSaveResponse(this.cnssService.create(this.cnss));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ICnss>>) {
        result.subscribe((res: HttpResponse<ICnss>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
