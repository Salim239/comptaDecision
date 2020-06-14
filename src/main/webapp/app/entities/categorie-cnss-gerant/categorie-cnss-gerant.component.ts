import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { ICategorieCnssGerant } from 'app/shared/model/categorie-cnss-gerant.model';
import { AccountService } from 'app/core';
import { CategorieCnssGerantService } from './categorie-cnss-gerant.service';

@Component({
    selector: 'jhi-secteur-activite',
    templateUrl: './categorie-cnss-gerant.component.html'
})
export class CategorieCnssGerantComponent implements OnInit, OnDestroy {
    categorieCnssGerants: ICategorieCnssGerant[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        protected categorieCnssGerantService: CategorieCnssGerantService,
        protected jhiAlertService: JhiAlertService,
        protected eventManager: JhiEventManager,
        protected accountService: AccountService
    ) {}

    loadAll() {
        this.categorieCnssGerantService
            .query()
            .pipe(
                filter((res: HttpResponse<ICategorieCnssGerant[]>) => res.ok),
                map((res: HttpResponse<ICategorieCnssGerant[]>) => res.body)
            )
            .subscribe(
                (res: ICategorieCnssGerant[]) => {
                    this.categorieCnssGerants = res;
                },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeIncategorieCnssGerants();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICategorieCnssGerant) {
        return item.id;
    }

    registerChangeIncategorieCnssGerants() {
        this.eventSubscriber = this.eventManager.subscribe('categorieCnssGerantListModification', response => this.loadAll());
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
