import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IImpotMensuel } from 'app/shared/model/impot-mensuel.model';

@Component({
    selector: 'jhi-impot-mensuel-detail',
    templateUrl: './impot-mensuel-detail.component.html'
})
export class ImpotMensuelDetailComponent implements OnInit {
    impotMensuel: IImpotMensuel;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ impotMensuel }) => {
            this.impotMensuel = impotMensuel;
        });
    }

    previousState() {
        window.history.back();
    }
}
