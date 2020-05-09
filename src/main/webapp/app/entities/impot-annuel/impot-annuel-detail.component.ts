import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IImpotAnnuel } from 'app/shared/model/impot-annuel.model';

@Component({
    selector: 'jhi-impot-annuel-detail',
    templateUrl: './impot-annuel-detail.component.html'
})
export class ImpotAnnuelDetailComponent implements OnInit {
    impotAnnuel: IImpotAnnuel;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ impotAnnuel }) => {
            this.impotAnnuel = impotAnnuel;
        });
    }

    previousState() {
        window.history.back();
    }
}
