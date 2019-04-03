import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IImpotMensuel } from 'app/shared/model/impot-mensuel.model';
import { AccountService } from 'app/core';
import { ImpotMensuelService } from './impot-mensuel.service';

@Component({
    selector: 'jhi-impot-mensuel',
    templateUrl: './impot-mensuel.component.html'
})
export class ImpotMensuelComponent implements OnInit, OnDestroy {
    impotMensuels: IImpotMensuel[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected impotMensuelService: ImpotMensuelService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.impotMensuelService
            .query()
            .pipe(
                filter((res: HttpResponse<IImpotMensuel[]>) => res.ok),
                map((res: HttpResponse<IImpotMensuel[]>) => res.body)
            )
            .subscribe(
                (res: IImpotMensuel[]) => {
                    this.impotMensuels = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInImpotMensuels();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IImpotMensuel) {
        return item.id;
    }

    registerChangeInImpotMensuels() {
        this.eventSubscriber = this.eventManager.subscribe('impotMensuelListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
