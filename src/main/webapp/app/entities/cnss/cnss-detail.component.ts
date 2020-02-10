import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICnss } from 'app/shared/model/cnss.model';

@Component({
    selector: 'jhi-cnss-detail',
    templateUrl: './cnss-detail.component.html'
})
export class CnssDetailComponent implements OnInit {
    cnss: ICnss;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cnss }) => {
            this.cnss = cnss;
        });
    }

    previousState() {
        window.history.back();
    }
}
