import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpErrorResponse, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiAlertService, JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { IQuittanceMensuelle } from 'app/shared/model/quittance-mensuelle.model';
import { AccountService } from 'app/core';

import { ITEMS_PER_PAGE } from 'app/shared';
import { QuittanceMensuelleService } from './quittance-mensuelle.service';

@Component({
    selector: 'jhi-quittance-mensuelle',
    styles: ['.table-dark.table-hover tbody tr::selection {color: red; background-color: red;}'],
    templateUrl: './quittance-mensuelle.component.html'
})
export class QuittanceMensuelleComponent implements OnInit, OnDestroy {
    currentAccount: any;
    quittanceMensuelles: IQuittanceMensuelle[];
    selectedQuittanceMensuelle: IQuittanceMensuelle;
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
        protected quittanceMensuelleService: QuittanceMensuelleService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.selectedQuittanceMensuelle = undefined;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.quittanceMensuelleService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IQuittanceMensuelle[]>) => this.paginateQuittanceMensuelles(res.body, res.headers),
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
        this.router.navigate(['/quittance-mensuelle'], {
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
            '/quittance-mensuelle',
            {
                page: this.page,
                sort: this.predicate + ',' + (this.reverse ? 'asc' : 'desc')
            }
        ]);
        this.loadAll();
    }

    ngOnInit() {
        this.loadAll();
        this.accountService.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInQuittanceMensuelles();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IQuittanceMensuelle) {
        return item.id;
    }

    registerChangeInQuittanceMensuelles() {
        this.eventSubscriber = this.eventManager.subscribe('quittanceMensuelleListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateQuittanceMensuelles(data: IQuittanceMensuelle[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.quittanceMensuelles = data;
    }

    protected onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    addNewQuittance(event) {
        const ficheClientId = event.ficheClientId;
        const mois = event.mois;
        const annee = event.annee;
        const typeDeclaration = event.typeDeclaration;
        console.log('ficheClientId ', ficheClientId);
        console.log('annee ', annee);
        console.log('mois ', mois);
        this.router.navigateByUrl(`/quittance-mensuelle/${ficheClientId}/${annee}/${mois}/${typeDeclaration}/new`);
    }

    selectRow(quittanceMensuelle: IQuittanceMensuelle) {
        // unselect selected row
        if (this.selectedQuittanceMensuelle && quittanceMensuelle.id === this.selectedQuittanceMensuelle.id) {
            this.selectedQuittanceMensuelle = undefined;
            // select row
        } else {
            this.selectedQuittanceMensuelle = quittanceMensuelle;
        }
    }

    delete() {
        if (this.selectedQuittanceMensuelle) {
            const id = this.selectedQuittanceMensuelle.id;
            this.router.navigateByUrl(`/quittance-mensuelle/${id}`);
        }
    }

    edit() {
        if (this.selectedQuittanceMensuelle) {
            const id = this.selectedQuittanceMensuelle.id;
            this.router.navigateByUrl(`/quittance-mensuelle/${id}`);
        }
    }

    view() {
        if (this.selectedQuittanceMensuelle) {
            const id = this.selectedQuittanceMensuelle.id;
            this.router.navigateByUrl(`/quittance-mensuelle/${id}`);
        }
    }

    isTrSelected(quittance: IQuittanceMensuelle) {
        return this.selectedQuittanceMensuelle && this.selectedQuittanceMensuelle.id === quittance.id;
    }

    // print() {
    //
    //     if (this.selectedQuittanceMensuelle) {
    //         const id = this.selectedQuittanceMensuelle.id;
    //         this.quittanceMensuelleService.print(id)
    //             .subscribe(
    //             (data) => {
    //                 var binaryData = [];
    //                 binaryData.push(data.body);
    //                 var blob = new Blob(binaryData, {type: 'application/pdf'});
    //                 const blobUrl = URL.createObjectURL(blob);
    //                 const iframe = document.createElement('iframe');
    //                 iframe.style.display = 'none';
    //                 iframe.src = blobUrl;
    //                 document.body.appendChild(iframe);
    //                 iframe.contentWindow.print();
    //             });
    //     }
    // }
}
