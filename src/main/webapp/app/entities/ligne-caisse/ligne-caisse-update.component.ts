import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {filter, map} from 'rxjs/operators';
import {JhiAlertService} from 'ng-jhipster';
import {ILigneCaisse} from 'app/shared/model/ligne-caisse.model';
import {LigneCaisseService} from './ligne-caisse.service';
import {IQuittanceMensuelle} from 'app/shared/model/quittance-mensuelle.model';
import {QuittanceMensuelleService} from 'app/entities/quittance-mensuelle';
import {IDeclarationAnnuelle} from 'app/shared/model/declaration-annuelle.model';
import {DeclarationAnnuelleService} from 'app/entities/declaration-annuelle';
import {ICnss} from 'app/shared/model/cnss.model';
import {CnssService} from 'app/entities/cnss';
import {ICaisse} from 'app/shared/model/caisse.model';
import {CaisseService} from 'app/entities/caisse';

@Component({
    selector: 'jhi-ligne-caisse-update',
    templateUrl: './ligne-caisse-update.component.html'
})
export class LigneCaisseUpdateComponent implements OnInit {
    ligneCaisse: ILigneCaisse;
    isSaving: boolean;

    quittancemensuelles: IQuittanceMensuelle[];

    declarationannuelles: IDeclarationAnnuelle[];

    cnsses: ICnss[];

    caisses: ICaisse[];

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected ligneCaisseService: LigneCaisseService,
        protected quittanceMensuelleService: QuittanceMensuelleService,
        protected declarationAnnuelleService: DeclarationAnnuelleService,
        protected cnssService: CnssService,
        protected caisseService: CaisseService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ ligneCaisse }) => {
            this.ligneCaisse = ligneCaisse;
        });
        this.quittanceMensuelleService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IQuittanceMensuelle[]>) => mayBeOk.ok),
                map((response: HttpResponse<IQuittanceMensuelle[]>) => response.body)
            )
            .subscribe(
                (res: IQuittanceMensuelle[]) => (this.quittancemensuelles = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.declarationAnnuelleService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IDeclarationAnnuelle[]>) => mayBeOk.ok),
                map((response: HttpResponse<IDeclarationAnnuelle[]>) => response.body)
            )
            .subscribe(
                (res: IDeclarationAnnuelle[]) => (this.declarationannuelles = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.cnssService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICnss[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICnss[]>) => response.body)
            )
            .subscribe((res: ICnss[]) => (this.cnsses = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.caisseService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICaisse[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICaisse[]>) => response.body)
            )
            .subscribe((res: ICaisse[]) => (this.caisses = res), (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.ligneCaisse.id !== undefined) {
            this.subscribeToSaveResponse(this.ligneCaisseService.update(this.ligneCaisse));
        } else {
            this.subscribeToSaveResponse(this.ligneCaisseService.create(this.ligneCaisse));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<ILigneCaisse>>) {
        result.subscribe((res: HttpResponse<ILigneCaisse>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackQuittanceMensuelleById(index: number, item: IQuittanceMensuelle) {
        return item.id;
    }

    trackDeclarationAnnuelleById(index: number, item: IDeclarationAnnuelle) {
        return item.id;
    }

    trackCnssById(index: number, item: ICnss) {
        return item.id;
    }

    trackCaisseById(index: number, item: ICaisse) {
        return item.id;
    }
}
