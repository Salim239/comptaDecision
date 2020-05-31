import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICentreAdministratif } from 'app/shared/model/centre-administratif.model';
import { AccountService } from 'app/core';
import { CentreAdministratifService } from './centre-administratif.service';

@Component({
    selector: 'jhi-centre-administratif',
    templateUrl: './centre-administratif.component.html'
})
export class CentreAdministratifComponent implements OnInit, OnDestroy {
    centreAdministratifs: ICentreAdministratif[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected centreAdministratifService: CentreAdministratifService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.centreAdministratifService
            .query()
            .pipe(
                filter((res: HttpResponse<ICentreAdministratif[]>) => res.ok),
                map((res: HttpResponse<ICentreAdministratif[]>) => res.body)
            )
            .subscribe(
                (res: ICentreAdministratif[]) => {
                    this.centreAdministratifs = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCentreAdministratifs();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICentreAdministratif) {
        return item.id;
    }

    registerChangeInCentreAdministratifs() {
        this.eventSubscriber = this.eventManager.subscribe('centreAdministratifListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
