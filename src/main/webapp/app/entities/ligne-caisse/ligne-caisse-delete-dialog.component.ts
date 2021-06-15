import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager} from 'ng-jhipster';

import {ILigneCaisse} from 'app/shared/model/ligne-caisse.model';
import {LigneCaisseService} from './ligne-caisse.service';

@Component({
    selector: 'jhi-ligne-caisse-delete-dialog',
    templateUrl: './ligne-caisse-delete-dialog.component.html'
})
export class LigneCaisseDeleteDialogComponent {
    ligneCaisse: ILigneCaisse;

    constructor(
        protected ligneCaisseService: LigneCaisseService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.ligneCaisseService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'ligneCaisseListModification',
                content: 'Deleted an ligneCaisse'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ligne-caisse-delete-popup',
    template: ''
})
export class LigneCaisseDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ligneCaisse }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(LigneCaisseDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.ligneCaisse = ligneCaisse;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/ligne-caisse', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/ligne-caisse', { outlets: { popup: null } }]);
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
