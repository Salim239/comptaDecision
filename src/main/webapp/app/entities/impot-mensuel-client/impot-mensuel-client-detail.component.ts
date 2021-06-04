import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IImpotMensuelClient } from 'app/shared/model/impot-mensuel-client.model';

@Component({
    selector: 'jhi-impot-mensuel-client-detail',
    templateUrl: './impot-mensuel-client-detail.component.html'
})
export class ImpotMensuelClientLigneComponent implements OnInit {
    impotMensuelClient: IImpotMensuelClient;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ impotMensuelClient }) => {
            this.impotMensuelClient = impotMensuelClient;
        });
    }

    previousState() {
        window.history.back();
    }
}
