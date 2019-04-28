import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuittanceMensuelleImpot } from 'app/shared/model/quittance-mensuelle-impot.model';
import {IQuittanceMensuelleImpotLine} from "app/shared/model/quittance-mensuelle-impot-line.model";
import * as _ from 'lodash';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-detail',
    templateUrl: './quittance-mensuelle-impot-detail.component.html'
})
export class QuittanceMensuelleImpotDetailComponent implements OnInit {
    quittanceMensuelleImpot: IQuittanceMensuelleImpot;
    quittanceMensuelleImpotApplicableLines: IQuittanceMensuelleImpotLine[];

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ quittanceMensuelleImpot }) => {
            this.quittanceMensuelleImpot = quittanceMensuelleImpot;
            this.quittanceMensuelleImpotApplicableLines =
                _.filter(this.quittanceMensuelleImpot.quittanceMensuelleImpotLines, function (quittanceMensuelleImpotLine) {
                    return quittanceMensuelleImpotLine.impotMensuelClient.applicable
            });
            this.quittanceMensuelleImpot.quittanceMensuelleImpotLines = this.quittanceMensuelleImpotApplicableLines;
        });
    }

    previousState() {
        window.history.back();
    }
}
