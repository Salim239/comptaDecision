import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAcompteProvisionnel } from 'app/shared/model/acompte-provisionnel.model';

@Component({
    selector: 'jhi-acompte-provisionnel-detail',
    templateUrl: './acompte-provisionnel-detail.component.html'
})
export class AcompteProvisionnelDetailComponent implements OnInit {
    acompteProvisionnel: IAcompteProvisionnel;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ acompteProvisionnel }) => {
            this.acompteProvisionnel = acompteProvisionnel;
        });
    }

    previousState() {
        window.history.back();
    }
}
