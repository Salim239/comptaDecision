import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuittanceMensuelle } from 'app/shared/model/quittance-mensuelle.model';
import { IQuittanceMensuelleLigne } from 'app/shared/model/quittance-mensuelle-ligne.model';
import * as _ from 'lodash';

@Component({
    selector: 'jhi-quittance-mensuelle-detail',
    templateUrl: './quittance-mensuelle-detail.component.html'
})
export class QuittanceMensuelleDetailComponent implements OnInit {
    quittanceMensuelle: IQuittanceMensuelle;
    quittanceMensuelleApplicableLines: IQuittanceMensuelleLigne[];

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ quittanceMensuelle }) => {
            this.quittanceMensuelle = quittanceMensuelle;
            this.quittanceMensuelleApplicableLines = _.filter(this.quittanceMensuelle.quittanceMensuelleLignes, function(
                quittanceMensuelleLigne
            ) {
                return quittanceMensuelleLigne.impotMensuelClient.applicable;
            });
            this.quittanceMensuelle.quittanceMensuelleLignes = this.quittanceMensuelleApplicableLines;
        });
    }

    previousState() {
        window.history.back();
    }
}
