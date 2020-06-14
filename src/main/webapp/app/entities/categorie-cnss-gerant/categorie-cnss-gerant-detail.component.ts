import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategorieCnssGerant } from 'app/shared/model/categorie-cnss-gerant.model';

@Component({
    selector: 'jhi-secteur-activite-detail',
    templateUrl: './categorie-cnss-gerant-detail.component.html'
})
export class CategorieCnssGerantDetailComponent implements OnInit {
    categorieCnssGerant: ICategorieCnssGerant;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ categorieCnssGerant }) => {
            this.categorieCnssGerant = categorieCnssGerant;
        });
    }

    previousState() {
        window.history.back();
    }
}
