import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICentreAdministratif } from 'app/shared/model/centre-administratif.model';

@Component({
    selector: 'jhi-centre-administratif-detail',
    templateUrl: './centre-administratif-detail.component.html'
})
export class CentreAdministratifLigneComponent implements OnInit {
    centreAdministratif: ICentreAdministratif;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ centreAdministratif }) => {
            this.centreAdministratif = centreAdministratif;
        });
    }

    previousState() {
        window.history.back();
    }
}
