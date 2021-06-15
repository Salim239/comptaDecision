import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager} from 'ng-jhipster';

import {ICaisse} from 'app/shared/model/caisse.model';
import {CaisseService} from './caisse.service';

@Component({
    selector: 'jhi-caisse-delete-dialog',
    templateUrl: './caisse-delete-dialog.component.html'
})
export class CaisseDeleteDialogComponent {
    caisse: ICaisse;

    constructor(protected caisseService: CaisseService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.caisseService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'caisseListModification',
                content: 'Deleted an caisse'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-caisse-delete-popup',
    template: ''
})
export class CaisseDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ caisse }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CaisseDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.caisse = caisse;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/caisse', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/caisse', { outlets: { popup: null } }]);
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
