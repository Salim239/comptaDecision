import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpErrorResponse, HttpHeaders, HttpResponse} from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {JhiAlertService, JhiEventManager, JhiParseLinks} from 'ng-jhipster';

import {IQuittanceMensuelleImpot} from 'app/shared/model/quittance-mensuelle-impot.model';
import {AccountService} from 'app/core';

import {ITEMS_PER_PAGE} from 'app/shared';
import {QuittanceMensuelleImpotService} from './quittance-mensuelle-impot.service';

@Component({
    selector: 'jhi-quittance-mensuelle-impot',
    styles: ['.table-dark.table-hover tbody tr::selection {color: red; background-color: red;}'],
    templateUrl: './quittance-mensuelle-impot.component.html'

})
export class QuittanceMensuelleImpotComponent implements OnInit, OnDestroy {
    currentAccount: any;
    quittanceMensuelleImpots: IQuittanceMensuelleImpot[];
    selectedQuittanceMensuelleImpot: IQuittanceMensuelleImpot;
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
        protected quittanceMensuelleImpotService: QuittanceMensuelleImpotService,
        protected parseLinks: JhiParseLinks,
        protected jhiAlertService: JhiAlertService,
        protected accountService: AccountService,
        protected activatedRoute: ActivatedRoute,
        protected router: Router,
        protected eventManager: JhiEventManager
    ) {
        this.itemsPerPage = ITEMS_PER_PAGE;
        this.selectedQuittanceMensuelleImpot = undefined;
        this.routeData = this.activatedRoute.data.subscribe(data => {
            this.page = data.pagingParams.page;
            this.previousPage = data.pagingParams.page;
            this.reverse = data.pagingParams.ascending;
            this.predicate = data.pagingParams.predicate;
        });
    }

    loadAll() {
        this.quittanceMensuelleImpotService
            .query({
                page: this.page - 1,
                size: this.itemsPerPage,
                sort: this.sort()
            })
            .subscribe(
                (res: HttpResponse<IQuittanceMensuelleImpot[]>) => this.paginateQuittanceMensuelleImpots(res.body, res.headers),
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
        this.router.navigate(['/quittance-mensuelle-impot'], {
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
            '/quittance-mensuelle-impot',
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
        this.registerChangeInQuittanceMensuelleImpots();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IQuittanceMensuelleImpot) {
        return item.id;
    }

    registerChangeInQuittanceMensuelleImpots() {
        this.eventSubscriber = this.eventManager.subscribe('quittanceMensuelleImpotListModification', response => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }

    protected paginateQuittanceMensuelleImpots(data: IQuittanceMensuelleImpot[], headers: HttpHeaders) {
        this.links = this.parseLinks.parse(headers.get('link'));
        this.totalItems = parseInt(headers.get('X-Total-Count'), 10);
        this.quittanceMensuelleImpots = data;
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
        this.router.navigateByUrl(`/quittance-mensuelle-impot/${ficheClientId}/${annee}/${mois}/${typeDeclaration}/new`);

    }

    selectRow(quittanceMensuelleImpot: IQuittanceMensuelleImpot) {

        // unselect selected row
        if (this.selectedQuittanceMensuelleImpot &&
        quittanceMensuelleImpot.id == this.selectedQuittanceMensuelleImpot.id) {
            this.selectedQuittanceMensuelleImpot = undefined
            // select row
        } else {
            this.selectedQuittanceMensuelleImpot = quittanceMensuelleImpot;
        }

    }

    delete() {
        if (this.selectedQuittanceMensuelleImpot) {
            const id = this.selectedQuittanceMensuelleImpot.id;
            this.router.navigateByUrl(`/quittance-mensuelle-impot/${id}`);
        }
    }

    edit() {
        if (this.selectedQuittanceMensuelleImpot) {
            const id = this.selectedQuittanceMensuelleImpot.id;
            this.router.navigateByUrl(`/quittance-mensuelle-impot/${id}`);
        }
    }

    view() {
        if (this.selectedQuittanceMensuelleImpot) {
            const id = this.selectedQuittanceMensuelleImpot.id;
            this.router.navigateByUrl(`/quittance-mensuelle-impot/${id}`);
        }
    }

    // print() {
    //
    //     if (this.selectedQuittanceMensuelleImpot) {
    //         const id = this.selectedQuittanceMensuelleImpot.id;
    //         this.quittanceMensuelleImpotService.print(id)
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
