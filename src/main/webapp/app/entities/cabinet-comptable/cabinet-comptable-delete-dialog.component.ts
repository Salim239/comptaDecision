import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager} from 'ng-jhipster';

import {ICabinetComptable} from 'app/shared/model/cabinet-comptable.model';
import {CabinetComptableService} from './cabinet-comptable.service';

@Component({
    selector: 'jhi-cabinet-comptable-delete-dialog',
    templateUrl: './cabinet-comptable-delete-dialog.component.html'
})
export class CabinetComptableDeleteDialogComponent {
    cabinetComptable: ICabinetComptable;

    constructor(
        protected cabinetComptableService: CabinetComptableService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cabinetComptableService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cabinetComptableListModification',
                content: 'Deleted an cabinetComptable'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cabinet-comptable-delete-popup',
    template: ''
})
export class CabinetComptableDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cabinetComptable }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CabinetComptableDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.cabinetComptable = cabinetComptable;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/cabinet-comptable', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/cabinet-comptable', { outlets: { popup: null } }]);
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
