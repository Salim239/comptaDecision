import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAcompteProvisionnel } from 'app/shared/model/acompte-provisionnel.model';
import { AcompteProvisionnelService } from './acompte-provisionnel.service';

@Component({
    selector: 'jhi-acompte-provisionnel-delete-dialog',
    templateUrl: './acompte-provisionnel-delete-dialog.component.html'
})
export class AcompteProvisionnelDeleteDialogComponent {
    acompteProvisionnel: IAcompteProvisionnel;

    constructor(
        protected acompteProvisionnelService: AcompteProvisionnelService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.acompteProvisionnelService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'acompteProvisionnelListModification',
                content: 'Deleted an acompteProvisionnel'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-acompte-provisionnel-delete-popup',
    template: ''
})
export class AcompteProvisionnelDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ acompteProvisionnel }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(AcompteProvisionnelDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.acompteProvisionnel = acompteProvisionnel;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/acompte-provisionnel', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/acompte-provisionnel', { outlets: { popup: null } }]);
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
