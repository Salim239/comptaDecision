import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {IImpotMensuel} from 'app/shared/model/impot-mensuel.model';
import {ImpotMensuelService} from './impot-mensuel.service';

@Component({
    selector: 'jhi-impot-mensuel-update',
    templateUrl: './impot-mensuel-update.component.html'
})
export class ImpotMensuelUpdateComponent implements OnInit {
    impotMensuel: IImpotMensuel;
    parentImpotMensuels: IImpotMensuel[];
    isSaving: boolean;

    constructor(protected impotMensuelService: ImpotMensuelService, protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ impotMensuel, parentImpotMensuels }) => {
            this.impotMensuel = impotMensuel;
            this.parentImpotMensuels = parentImpotMensuels;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.impotMensuel.id !== undefined) {
            this.subscribeToSaveResponse(this.impotMensuelService.update(this.impotMensuel));
        } else {
            this.subscribeToSaveResponse(this.impotMensuelService.create(this.impotMensuel));
        }
    }

    protected subscribeToSaveResponse(result: Observable<HttpResponse<IImpotMensuel>>) {
        result.subscribe((res: HttpResponse<IImpotMensuel>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    protected onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    protected onSaveError() {
        this.isSaving = false;
    }
}
