import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICnss } from 'app/shared/model/cnss.model';
import { CnssService } from './cnss.service';

@Component({
    selector: 'jhi-cnss-delete-dialog',
    templateUrl: './cnss-delete-dialog.component.html'
})
export class CnssDeleteDialogComponent {
    cnss: ICnss;

    constructor(protected cnssService: CnssService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.cnssService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cnssListModification',
                content: 'Deleted an cnss'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-cnss-delete-popup',
    template: ''
})
export class CnssDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cnss }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CnssDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.cnss = cnss;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/cnss', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/cnss', { outlets: { popup: null } }]);
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
