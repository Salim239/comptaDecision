import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {IQuittanceMensuelleImpot} from 'app/shared/model/quittance-mensuelle-impot.model';
import {IQuittanceMensuelleImpotDetail} from "app/shared/model/quittance-mensuelle-impot-detail.model";
import * as _ from 'lodash';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-detail',
    templateUrl: './quittance-mensuelle-impot-detail.component.html'
})
export class QuittanceMensuelleImpotDetailComponent implements OnInit {
    quittanceMensuelleImpot: IQuittanceMensuelleImpot;
    quittanceMensuelleImpotApplicableLines: IQuittanceMensuelleImpotDetail[];

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ quittanceMensuelleImpot }) => {
            this.quittanceMensuelleImpot = quittanceMensuelleImpot;
            this.quittanceMensuelleImpotApplicableLines =
                _.filter(this.quittanceMensuelleImpot.quittanceMensuelleImpotDetails, function (quittanceMensuelleImpotDetail) {
                    return quittanceMensuelleImpotDetail.impotMensuelClient.applicable
            });
            this.quittanceMensuelleImpot.quittanceMensuelleImpotDetails = this.quittanceMensuelleImpotApplicableLines;
        });
    }

    previousState() {
        window.history.back();
    }
}
