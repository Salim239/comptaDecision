import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDeclarationAnnuelle } from 'app/shared/model/declaration-annuelle.model';

@Component({
    selector: 'jhi-declaration-annuelle-detail',
    templateUrl: './declaration-annuelle-detail.component.html'
})
export class DeclarationAnnuelleLigneComponent implements OnInit {
    declarationAnnuelle: IDeclarationAnnuelle;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ declarationAnnuelle }) => {
            this.declarationAnnuelle = declarationAnnuelle;
        });
    }

    previousState() {
        window.history.back();
    }
}
