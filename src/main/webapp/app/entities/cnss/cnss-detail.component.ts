import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICnss, TypeCnss } from 'app/shared/model/cnss.model';

@Component({
    selector: 'jhi-cnss-detail',
    templateUrl: './cnss-detail.component.html'
})
export class CnssDetailComponent implements OnInit {
    cnss: ICnss;
    isTypeCnssGerant: Boolean;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cnss }) => {
            this.cnss = cnss;
            this.isTypeCnssGerant = this.cnss.typeCnss === TypeCnss.CNSS_EMPLOYEUR;
        });
    }

    previousState() {
        window.history.back();
    }
}
