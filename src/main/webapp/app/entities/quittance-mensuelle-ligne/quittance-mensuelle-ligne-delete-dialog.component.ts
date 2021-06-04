import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQuittanceMensuelleLigne } from 'app/shared/model/quittance-mensuelle-ligne.model';
import { QuittanceMensuelleLigneService } from './quittance-mensuelle-ligne.service';

@Component({
    selector: 'jhi-quittance-mensuelle-ligne-delete-dialog',
    templateUrl: './quittance-mensuelle-ligne-delete-dialog.component.html'
})
export class QuittanceMensuelleLigneDeleteDialogComponent {
    quittanceMensuelleLigne: IQuittanceMensuelleLigne;

    constructor(
        protected quittanceMensuelleLigneService: QuittanceMensuelleLigneService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quittanceMensuelleLigneService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'quittanceMensuelleLigneListModification',
                content: 'Deleted an quittanceMensuelleLigne'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quittance-mensuelle-ligne-delete-popup',
    template: ''
})
export class QuittanceMensuelleLigneDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ quittanceMensuelleLigne }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(QuittanceMensuelleLigneDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.quittanceMensuelleLigne = quittanceMensuelleLigne;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/quittance-mensuelle-ligne', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/quittance-mensuelle-ligne', { outlets: { popup: null } }]);
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
