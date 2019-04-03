import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISecteurActivite } from 'app/shared/model/secteur-activite.model';

@Component({
    selector: 'jhi-secteur-activite-detail',
    templateUrl: './secteur-activite-detail.component.html'
})
export class SecteurActiviteDetailComponent implements OnInit {
    secteurActivite: ISecteurActivite;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ secteurActivite }) => {
            this.secteurActivite = secteurActivite;
        });
    }

    previousState() {
        window.history.back();
    }
}
