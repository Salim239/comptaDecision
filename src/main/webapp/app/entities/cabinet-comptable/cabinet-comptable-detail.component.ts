import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {ICabinetComptable} from 'app/shared/model/cabinet-comptable.model';

@Component({
    selector: 'jhi-cabinet-comptable-detail',
    templateUrl: './cabinet-comptable-detail.component.html'
})
export class CabinetComptableDetailComponent implements OnInit {
    cabinetComptable: ICabinetComptable;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cabinetComptable }) => {
            this.cabinetComptable = cabinetComptable;
        });
    }

    previousState() {
        window.history.back();
    }
}
