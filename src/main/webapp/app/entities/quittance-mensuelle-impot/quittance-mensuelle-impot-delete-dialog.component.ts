import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQuittanceMensuelleImpot } from 'app/shared/model/quittance-mensuelle-impot.model';
import { QuittanceMensuelleImpotService } from './quittance-mensuelle-impot.service';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-delete-dialog',
    templateUrl: './quittance-mensuelle-impot-delete-dialog.component.html'
})
export class QuittanceMensuelleImpotDeleteDialogComponent {
    quittanceMensuelleImpot: IQuittanceMensuelleImpot;

    constructor(
        protected quittanceMensuelleImpotService: QuittanceMensuelleImpotService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quittanceMensuelleImpotService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'quittanceMensuelleImpotListModification',
                content: 'Deleted an quittanceMensuelleImpot'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quittance-mensuelle-impot-delete-popup',
    template: ''
})
export class QuittanceMensuelleImpotDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ quittanceMensuelleImpot }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(QuittanceMensuelleImpotDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.quittanceMensuelleImpot = quittanceMensuelleImpot;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/quittance-mensuelle-impot', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/quittance-mensuelle-impot', { outlets: { popup: null } }]);
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
