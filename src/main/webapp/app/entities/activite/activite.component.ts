import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IActivite } from 'app/shared/model/activite.model';
import { AccountService } from 'app/core';
import { ActiviteService } from './activite.service';

@Component({
    selector: 'jhi-activite',
    templateUrl: './activite.component.html'
})
export class ActiviteComponent implements OnInit, OnDestroy {
    activites: IActivite[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected activiteService: ActiviteService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.activiteService
            .query()
            .pipe(
                filter((res: HttpResponse<IActivite[]>) => res.ok),
                map((res: HttpResponse<IActivite[]>) => res.body)
            )
            .subscribe(
                (res: IActivite[]) => {
                    this.activites = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInActivites();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IActivite) {
        return item.id;
    }

    registerChangeInActivites() {
        this.eventSubscriber = this.eventManager.subscribe('activiteListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
