import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuittanceMensuelleImpot } from 'app/shared/model/quittance-mensuelle-impot.model';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-detail',
    templateUrl: './quittance-mensuelle-impot-detail.component.html'
})
export class QuittanceMensuelleImpotDetailComponent implements OnInit {
    quittanceMensuelleImpot: IQuittanceMensuelleImpot;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ quittanceMensuelleImpot }) => {
            this.quittanceMensuelleImpot = quittanceMensuelleImpot;
        });
    }

    previousState() {
        window.history.back();
    }
}
