import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IImpotAnnuel } from 'app/shared/model/impot-annuel.model';
import { ImpotAnnuelService } from './impot-annuel.service';

@Component({
    selector: 'jhi-impot-annuel-delete-dialog',
    templateUrl: './impot-annuel-delete-dialog.component.html'
})
export class ImpotAnnuelDeleteDialogComponent {
    impotAnnuel: IImpotAnnuel;

    constructor(
        protected impotAnnuelService: ImpotAnnuelService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.impotAnnuelService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'impotAnnuelListModification',
                content: 'Deleted an impotAnnuel'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-impot-annuel-delete-popup',
    template: ''
})
export class ImpotAnnuelDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ impotAnnuel }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ImpotAnnuelDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.impotAnnuel = impotAnnuel;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/impot-annuel', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/impot-annuel', { outlets: { popup: null } }]);
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
