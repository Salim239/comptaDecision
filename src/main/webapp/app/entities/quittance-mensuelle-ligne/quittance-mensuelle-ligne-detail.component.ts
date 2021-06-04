import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuittanceMensuelleLigne } from 'app/shared/model/quittance-mensuelle-ligne.model';

@Component({
    selector: 'jhi-quittance-mensuelle-ligne-detail',
    templateUrl: './quittance-mensuelle-ligne-detail.component.html'
})
export class QuittanceMensuelleLigneLigneComponent implements OnInit {
    quittanceMensuelleLigne: IQuittanceMensuelleLigne;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ quittanceMensuelleLigne }) => {
            this.quittanceMensuelleLigne = quittanceMensuelleLigne;
        });
    }

    previousState() {
        window.history.back();
    }
}
