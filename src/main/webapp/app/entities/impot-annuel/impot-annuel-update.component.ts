import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IImpotAnnuel } from 'app/shared/model/impot-annuel.model';
import { ImpotAnnuelService } from './impot-annuel.service';
import { IImpotMensuelLigne, TypeValeur } from 'app/shared/model/impot-mensuel-ligne.model';
import { IImpotAnnuelLigne, ImpotAnnuelLigne } from 'app/shared/model/impot-annuel-ligne.model';

@Component({
    selector: 'jhi-impot-annuel-update',
    templateUrl: './impot-annuel-update.component.html'
})
export class ImpotAnnuelUpdateComponent implements OnInit {
    impotAnnuel: IImpotAnnuel;
    impotAnnuelLigneNew: IImpotAnnuelLigne;
    impotMensuelLignes: IImpotMensuelLigne[];
    impotAnnuelEnfants: IImpotAnnuel[];
    isSaving: boolean;

    constructor(protected impotAnnuelService: ImpotAnnuelService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ impotAnnuel, impotMensuelLignes, impotAnnuelEnfants }) => {
            this.impotAnnuel = impotAnnuel;
            this.impotMensuelLignes = impotMensuelLignes;
            this.impotAnnuelEnfants = impotAnnuelEnfants;
            this.impotAnnuelLigneNew = this.newImpotAnnuelLigne();
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

    formatImpotMensuelLigneLibelle(impotMensueldetail: IImpotMensuelLigne) {
        let libelle = impotMensueldetail.libelle;
        if (impotMensueldetail.typeValeur === TypeValeur.TAUX) {
            libelle = libelle + ' (' + impotMensueldetail.valeur + '%)';
        } else if (impotMensueldetail.valeur) {
            libelle = libelle + ' (' + impotMensueldetail.valeur + 'TND)';
        }
        return libelle;
    }

    newImpotAnnuelLigne() {
        return new ImpotAnnuelLigne(
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

    addImpotAnnuelLigne(impotAnnuelLigneNew: IImpotAnnuelLigne) {
        this.impotAnnuel.impotAnnuelLignes.push(impotAnnuelLigneNew);
        this.impotAnnuelLigneNew = this.newImpotAnnuelLigne();
    }

    deleteImpotAnnuelLigne(impotAnnuelLigneIndex) {
        this.impotAnnuel.impotAnnuelLignes.splice(impotAnnuelLigneIndex, 1);
    }

    isValidImpotAnnuelLigne(impotAnnuelLigne) {
        return impotAnnuelLigne && impotAnnuelLigne.impotMensuelLigneId && impotAnnuelLigne.coefficient;
    }
}
