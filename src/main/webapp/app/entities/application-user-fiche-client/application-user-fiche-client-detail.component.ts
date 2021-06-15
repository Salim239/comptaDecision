import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

import {IApplicationUserFicheClient} from 'app/shared/model/application-user-fiche-client.model';

@Component({
    selector: 'jhi-application-user-fiche-client-detail',
    templateUrl: './application-user-fiche-client-detail.component.html'
})
export class ApplicationUserFicheClientDetailComponent implements OnInit {
    applicationUserFicheClient: IApplicationUserFicheClient;

    constructor(protected activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ applicationUserFicheClient }) => {
            this.applicationUserFicheClient = applicationUserFicheClient;
        });
    }

    previousState() {
        window.history.back();
    }
}
