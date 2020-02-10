import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IVille } from 'app/shared/model/ville.model';
import { AccountService } from 'app/core';
import { VilleService } from './ville.service';

@Component({
    selector: 'jhi-ville',
    templateUrl: './ville.component.html'
})
export class VilleComponent implements OnInit, OnDestroy {
    villes: IVille[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected villeService: VilleService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.villeService
            .query()
            .pipe(
                filter((res: HttpResponse<IVille[]>) => res.ok),
                map((res: HttpResponse<IVille[]>) => res.body)
            )
            .subscribe(
                (res: IVille[]) => {
                    this.villes = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInVilles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IVille) {
        return item.id;
    }

    registerChangeInVilles() {
        this.eventSubscriber = this.eventManager.subscribe('villeListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
