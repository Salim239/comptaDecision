import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICentreAdministratif } from 'app/shared/model/centre-administratif.model';
import { CentreAdministratifService } from './centre-administratif.service';

@Component({
    selector: 'jhi-centre-administratif-delete-dialog',
    templateUrl: './centre-administratif-delete-dialog.component.html'
})
export class CentreAdministratifDeleteDialogComponent {
    centreAdministratif: ICentreAdministratif;

    constructor(
        protected centreAdministratifService: CentreAdministratifService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.centreAdministratifService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'centreAdministratifListModification',
                content: 'Deleted an centreAdministratif'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-centre-administratif-delete-popup',
    template: ''
})
export class CentreAdministratifDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ centreAdministratif }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CentreAdministratifDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.centreAdministratif = centreAdministratif;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/centre-administratif', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/centre-administratif', { outlets: { popup: null } }]);
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
