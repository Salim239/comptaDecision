import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {ILigneCaisse} from 'app/shared/model/ligne-caisse.model';

@Component({
    selector: 'jhi-ligne-caisse-detail',
    templateUrl: './ligne-caisse-detail.component.html'
})
export class LigneCaisseDetailComponent implements OnInit {
    ligneCaisse: ILigneCaisse;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ligneCaisse }) => {
            this.ligneCaisse = ligneCaisse;
        });
    }

    previousState() {
        window.history.back();
    }
}
