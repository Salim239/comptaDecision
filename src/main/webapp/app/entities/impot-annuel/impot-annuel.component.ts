import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiEventManager } from 'ng-jhipster';

import { IImpotAnnuel } from 'app/shared/model/impot-annuel.model';
import { AccountService } from 'app/core';
import { ImpotAnnuelService } from './impot-annuel.service';

@Component({
    selector: 'jhi-impot-annuel',
    templateUrl: './impot-annuel.component.html'
})
export class ImpotAnnuelComponent implements OnInit, OnDestroy {
    impotAnnuels: IImpotAnnuel[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected impotAnnuelService: ImpotAnnuelService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.impotAnnuelService
            .query()
            .pipe(
                filter((res: HttpResponse<IImpotAnnuel[]>) => res.ok),
                map((res: HttpResponse<IImpotAnnuel[]>) => res.body)
            )
            .subscribe(
                (res: IImpotAnnuel[]) => {
                    this.impotAnnuels = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInImpotAnnuels();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IImpotAnnuel) {
        return item.id;
    }

    registerChangeInImpotAnnuels() {
        this.eventSubscriber = this.eventManager.subscribe('impotAnnuelListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
