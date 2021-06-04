import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDeclarationEmployeurAnnuelle } from 'app/shared/model/declaration-employeur-annuelle.model';

@Component({
    selector: 'jhi-declaration-employeur-annuelle-detail',
    templateUrl: './declaration-employeur-annuelle-detail.component.html'
})
export class DeclarationEmployeurAnnuelleLigneComponent implements OnInit {
    declarationEmployeurAnnuelle: IDeclarationEmployeurAnnuelle;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ declarationEmployeurAnnuelle }) => {
            this.declarationEmployeurAnnuelle = declarationEmployeurAnnuelle;
        });
    }

    previousState() {
        window.history.back();
    }
}
