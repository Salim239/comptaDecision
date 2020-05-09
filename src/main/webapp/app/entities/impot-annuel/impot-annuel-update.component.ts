import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IImpotAnnuel } from 'app/shared/model/impot-annuel.model';
import { ImpotAnnuelService } from './impot-annuel.service';
import { IImpotMensuelDetail, TypeValeur } from 'app/shared/model/impot-mensuel-detail.model';
import { IImpotAnnuelDetail, ImpotAnnuelDetail } from 'app/shared/model/impot-annuel-detail.model';

@Component({
    selector: 'jhi-impot-annuel-update',
    templateUrl: './impot-annuel-update.component.html'
})
export class ImpotAnnuelUpdateComponent implements OnInit {
    impotAnnuel: IImpotAnnuel;
    impotAnnuelDetailNew: IImpotAnnuelDetail;
    impotMensuelDetails: IImpotMensuelDetail[];
    isSaving: boolean;

    constructor(protected impotAnnuelService: ImpotAnnuelService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ impotAnnuel, impotMensuelDetails }) => {
            this.impotAnnuel = impotAnnuel;
            this.impotMensuelDetails = impotMensuelDetails;
            this.impotAnnuelDetailNew = this.newImpotAnnuelDetail();
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.impotAnnuel.id !== undefined) {
            this.subscribeToSaveResponse(this.impotAnnuelService.update(this.impotAnnuel));
        } else {
            this.subscribeToSaveResponse(this.impotAnnuelService.create(this.impotAnnuel));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IImpotAnnuel>>) {
        result.subscribe((res: HttpResponse<IImpotAnnuel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }

    formatImpotMensuelDetailLibelle(impotMensueldetail: IImpotMensuelDetail) {
        let libelle = impotMensueldetail.libelle;
        if (impotMensueldetail.typeValeur === TypeValeur.TAUX) {
            libelle = libelle + ' (' + impotMensueldetail.valeur + '%)';
        } else if (impotMensueldetail.valeur) {
            libelle = libelle + ' (' + impotMensueldetail.valeur + 'TND)';
        }
        return libelle;
    }

    newImpotAnnuelDetail() {
        return new ImpotAnnuelDetail(
            undefined,
            this.impotAnnuel.id,
            this.impotAnnuel.libelle,
            undefined,
            undefined,
            undefined,
            undefined,
            1
        );
    }

    addImpotAnnuelDetail(impotAnnuelDetailNew: IImpotAnnuelDetail) {
        this.impotAnnuel.impotAnnuelDetails.push(impotAnnuelDetailNew);
        this.impotAnnuelDetailNew = this.newImpotAnnuelDetail();
    }

    // editImpotAnnuelDetail(impotAnnuelDetail, impotAnnuelDetailIndex) {
    //     this.impotAnnuelDetailNew = impotAnnuelDetail;
    //     this.impotAnnuel.impotAnnuelDetails.splice(impotAnnuelDetailIndex, 1);
    // }

    deleteImpotAnnuelDetail(impotAnnuelDetailIndex) {
        this.impotAnnuel.impotAnnuelDetails.splice(impotAnnuelDetailIndex, 1);
    }

    isValidImpotAnnuelDetail(impotAnnuelDetail) {
        return impotAnnuelDetail && impotAnnuelDetail.impotMensuelDetailId && impotAnnuelDetail.coefficient;
    }
}
