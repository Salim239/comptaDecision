import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQuittanceMensuelle } from 'app/shared/model/quittance-mensuelle.model';
import { QuittanceMensuelleService } from './quittance-mensuelle.service';

@Component({
    selector: 'jhi-quittance-mensuelle-delete-dialog',
    templateUrl: './quittance-mensuelle-delete-dialog.component.html'
})
export class QuittanceMensuelleDeleteDialogComponent {
    quittanceMensuelle: IQuittanceMensuelle;

    constructor(
        protected quittanceMensuelleService: QuittanceMensuelleService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quittanceMensuelleService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'quittanceMensuelleListModification',
                content: 'Deleted an quittanceMensuelle'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quittance-mensuelle-delete-popup',
    template: ''
})
export class QuittanceMensuelleDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ quittanceMensuelle }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(QuittanceMensuelleDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.quittanceMensuelle = quittanceMensuelle;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/quittance-mensuelle', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/quittance-mensuelle', { outlets: { popup: null } }]);
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
