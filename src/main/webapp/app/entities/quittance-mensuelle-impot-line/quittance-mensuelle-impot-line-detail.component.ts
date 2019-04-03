import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuittanceMensuelleImpotLine } from 'app/shared/model/quittance-mensuelle-impot-line.model';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-line-detail',
    templateUrl: './quittance-mensuelle-impot-line-detail.component.html'
})
export class QuittanceMensuelleImpotLineDetailComponent implements OnInit {
    quittanceMensuelleImpotLine: IQuittanceMensuelleImpotLine;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ quittanceMensuelleImpotLine }) => {
            this.quittanceMensuelleImpotLine = quittanceMensuelleImpotLine;
        });
    }

    previousState() {
        window.history.back();
    }
}
