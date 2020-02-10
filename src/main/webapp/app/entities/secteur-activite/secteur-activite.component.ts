import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ISecteurActivite } from 'app/shared/model/secteur-activite.model';
import { AccountService } from 'app/core';
import { SecteurActiviteService } from './secteur-activite.service';

@Component({
    selector: 'jhi-secteur-activite',
    templateUrl: './secteur-activite.component.html'
})
export class SecteurActiviteComponent implements OnInit, OnDestroy {
    secteurActivites: ISecteurActivite[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected secteurActiviteService: SecteurActiviteService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.secteurActiviteService
            .query()
            .pipe(
                filter((res: HttpResponse<ISecteurActivite[]>) => res.ok),
                map((res: HttpResponse<ISecteurActivite[]>) => res.body)
            )
            .subscribe(
                (res: ISecteurActivite[]) => {
                    this.secteurActivites = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInSecteurActivites();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ISecteurActivite) {
        return item.id;
    }

    registerChangeInSecteurActivites() {
        this.eventSubscriber = this.eventManager.subscribe('secteurActiviteListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
