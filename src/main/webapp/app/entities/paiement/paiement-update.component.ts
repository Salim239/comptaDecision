import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPaiement } from 'app/shared/model/paiement.model';
import { PaiementService } from './paiement.service';
import { ILigneCaisse } from 'app/shared/model/ligne-caisse.model';
import { LigneCaisseService } from 'app/entities/ligne-caisse';
import { IFicheClient } from 'app/shared/model/fiche-client.model';
import { FicheClientService } from 'app/entities/fiche-client';
import { ICnss } from 'app/shared/model/cnss.model';
import { CnssService } from 'app/entities/cnss';
import { IQuittanceMensuelle } from 'app/shared/model/quittance-mensuelle.model';
import { QuittanceMensuelleService } from 'app/entities/quittance-mensuelle';
import { IDeclarationAnnuelle } from 'app/shared/model/declaration-annuelle.model';
import { DeclarationAnnuelleService } from 'app/entities/declaration-annuelle';
import { IDeclarationEmployeurAnnuelle } from 'app/shared/model/declaration-employeur-annuelle.model';
import { DeclarationEmployeurAnnuelleService } from 'app/entities/declaration-employeur-annuelle';
import { IAcompteProvisionnel } from 'app/shared/model/acompte-provisionnel.model';
import { AcompteProvisionnelService } from 'app/entities/acompte-provisionnel';

@Component({
    selector: 'jhi-paiement-update',
    templateUrl: './paiement-update.component.html'
})
export class PaiementUpdateComponent implements OnInit {
    paiement: IPaiement;
    isSaving: boolean;

    lignecaisses: ILigneCaisse[];

    ficheclients: IFicheClient[];

    cnsses: ICnss[];

    quittancemensuelles: IQuittanceMensuelle[];

    declarationannuelles: IDeclarationAnnuelle[];

    declarationemployeurannuelles: IDeclarationEmployeurAnnuelle[];

    acompteprovisionnels: IAcompteProvisionnel[];
    datePaiementDp: any;

    constructor(
        protected jhiAlertService: JhiAlertService,
        protected paiementService: PaiementService,
        protected ligneCaisseService: LigneCaisseService,
        protected ficheClientService: FicheClientService,
        protected cnssService: CnssService,
        protected quittanceMensuelleService: QuittanceMensuelleService,
        protected declarationAnnuelleService: DeclarationAnnuelleService,
        protected declarationEmployeurAnnuelleService: DeclarationEmployeurAnnuelleService,
        protected acompteProvisionnelService: AcompteProvisionnelService,
        protected activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ paiement }) => {
            this.paiement = paiement;
        });
        this.ligneCaisseService
            .query({ 'paiementId.specified': 'false' })
            .pipe(
                filter((mayBeOk: HttpResponse<ILigneCaisse[]>) => mayBeOk.ok),
                map((response: HttpResponse<ILigneCaisse[]>) => response.body)
            )
            .subscribe(
                (res: ILigneCaisse[]) => {
                    if (!this.paiement.ligneCaisseId) {
                        this.lignecaisses = res;
                    } else {
                        this.ligneCaisseService
                            .find(this.paiement.ligneCaisseId)
                            .pipe(
                                filter((subResMayBeOk: HttpResponse<ILigneCaisse>) => subResMayBeOk.ok),
                                map((subResponse: HttpResponse<ILigneCaisse>) => subResponse.body)
                            )
                            .subscribe(
                                (subRes: ILigneCaisse) => (this.lignecaisses = [subRes].concat(res)),
                                (subRes: HttpErrorResponse) => this.onError(subRes.message)
                            );
                    }
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.ficheClientService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IFicheClient[]>) => mayBeOk.ok),
                map((response: HttpResponse<IFicheClient[]>) => response.body)
            )
            .subscribe((res: IFicheClient[]) => (this.ficheclients = res), (res: HttpErrorResponse) => this.onError(res.message));
        this.cnssService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<ICnss[]>) => mayBeOk.ok),
                map((response: HttpResponse<ICnss[]>) => response.body)
            )
            .subscribe((res: ICnss[]) => (this.cnsses = res), (res: HttpErrorResponse) => this.onError(res.message));
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
        this.declarationEmployeurAnnuelleService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IDeclarationEmployeurAnnuelle[]>) => mayBeOk.ok),
                map((response: HttpResponse<IDeclarationEmployeurAnnuelle[]>) => response.body)
            )
            .subscribe(
                (res: IDeclarationEmployeurAnnuelle[]) => (this.declarationemployeurannuelles = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        this.acompteProvisionnelService
            .query()
            .pipe(
                filter((mayBeOk: HttpResponse<IAcompteProvisionnel[]>) => mayBeOk.ok),
                map((response: HttpResponse<IAcompteProvisionnel[]>) => response.body)
            )
            .subscribe(
                (res: IAcompteProvisionnel[]) => (this.acompteprovisionnels = res),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.paiement.id !== undefined) {
            this.subscribeToSaveResponse(this.paiementService.update(this.paiement));
        } else {
            this.subscribeToSaveResponse(this.paiementService.create(this.paiement));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaiement>>) {
        result.subscribe((res: HttpResponse<IPaiement>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackLigneCaisseById(index: number, item: ILigneCaisse) {
        return item.id;
    }

    trackFicheClientById(index: number, item: IFicheClient) {
        return item.id;
    }

    trackCnssById(index: number, item: ICnss) {
        return item.id;
    }

    trackQuittanceMensuelleById(index: number, item: IQuittanceMensuelle) {
        return item.id;
    }

    trackDeclarationAnnuelleById(index: number, item: IDeclarationAnnuelle) {
        return item.id;
    }

    trackDeclarationEmployeurAnnuelleById(index: number, item: IDeclarationEmployeurAnnuelle) {
        return item.id;
    }

    trackAcompteProvisionnelById(index: number, item: IAcompteProvisionnel) {
        return item.id;
    }
}
