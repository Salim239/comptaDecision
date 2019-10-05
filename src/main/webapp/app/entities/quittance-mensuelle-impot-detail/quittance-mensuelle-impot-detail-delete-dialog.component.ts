import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager} from 'ng-jhipster';

import {IQuittanceMensuelleImpotDetail} from 'app/shared/model/quittance-mensuelle-impot-detail.model';
import {QuittanceMensuelleImpotDetailService} from './quittance-mensuelle-impot-detail.service';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-detail-delete-dialog',
    templateUrl: './quittance-mensuelle-impot-detail-delete-dialog.component.html'
})
export class QuittanceMensuelleImpotDetailDeleteDialogComponent {
    quittanceMensuelleImpotDetail: IQuittanceMensuelleImpotDetail;

    constructor(
        protected quittanceMensuelleImpotDetailService: QuittanceMensuelleImpotDetailService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quittanceMensuelleImpotDetailService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'quittanceMensuelleImpotDetailListModification',
                content: 'Deleted an quittanceMensuelleImpotDetail'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quittance-mensuelle-impot-detail-delete-popup',
    template: ''
})
export class QuittanceMensuelleImpotDetailDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ quittanceMensuelleImpotDetail }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(QuittanceMensuelleImpotDetailDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.quittanceMensuelleImpotDetail = quittanceMensuelleImpotDetail;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/quittance-mensuelle-impot-detail', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/quittance-mensuelle-impot-detail', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
