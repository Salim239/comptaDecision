import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiAlertService, JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { ICnss, TypeCnss } from 'app/shared/model/cnss.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { CnssService } from './cnss.service';

@Component({
    selector: 'jhi-cnss',
    templateUrl: './cnss.component.html'
})
export class CnssComponent implements OnInit, OnDestroy {
    currentAccount: any;
    cnssGerant: TypeCnss = TypeCnss.CNSS_EMPLOYEUR;
    isCurrentTypeCnssGerant: boolean;
    cnss: ICnss[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    routeData: any;
    links: any;
    totalItems: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    constructor(
        protected cnssService: CnssService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.cnssService
            .query(
                {
                    page: this.page - 1,
                    size: this.itemsPerPage,
                    sort: this.sort()
                },
                this.isCurrentTypeCnssGerant
            )
            .subscribe(
                (res: HttpResponse<ICnss[]>) => this.paginateCnss(res.body, res.headers),
                (res: HttpErrorResponse) => this.onError(res.message)
            );
    }

    loadPage(page: number) {
        if (page !== this.previousPage) {
            this.previousPage = page;
            this.transition();
        }
    }

    transition() {
        this.router.navigate(['/cnss'], {
            queryParams: {
                page: this.page,
                size: this.itemsPerPage,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        });
        this.loadAll();
    }

    clear() {
        this.page = 0;
        this.router.navigate([
            '/cnss',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.isCurrentTypeCnssGerant = this.router.url.includes('gerant');
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInCnss();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ICnss) {
        return item.id;
    }

    registerChangeInCnss() {
        this.eventSubscriber = this.eventManager.subscribe('cnssListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateCnss(data: ICnss[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.cnss = data;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    addCnss(event) {
        const ficheClientId = event.ficheClientId;
        const annee = event.annee;
        const trimestre = event.trimestre;
        const typeDeclarationCnss = event.typeDeclarationCnss;
        const typeCnss = this.isCurrentTypeCnssGerant ? 'CNSS_EMPLOYEUR' : 'CNSS_GENERALE';
        this.router.navigateByUrl(`/cnss/${ficheClientId}/${annee}/${typeCnss}/${typeDeclarationCnss}/${trimestre}/new`);
    }
}
