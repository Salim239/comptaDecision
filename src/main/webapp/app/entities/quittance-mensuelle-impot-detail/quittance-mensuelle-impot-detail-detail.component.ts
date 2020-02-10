import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {IQuittanceMensuelleImpotDetail} from 'app/shared/model/quittance-mensuelle-impot-detail.model';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-detail-detail',
    templateUrl: './quittance-mensuelle-impot-detail-detail.component.html'
})
export class QuittanceMensuelleImpotDetailDetailComponent implements OnInit {
    quittanceMensuelleImpotDetail: IQuittanceMensuelleImpotDetail;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ quittanceMensuelleImpotDetail }) => {
            this.quittanceMensuelleImpotDetail = quittanceMensuelleImpotDetail;
        });
    }

    previousState() {
        window.history.back();
    }
}
