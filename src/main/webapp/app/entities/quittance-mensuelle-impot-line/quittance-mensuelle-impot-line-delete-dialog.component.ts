import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQuittanceMensuelleImpotLine } from 'app/shared/model/quittance-mensuelle-impot-line.model';
import { QuittanceMensuelleImpotLineService } from './quittance-mensuelle-impot-line.service';

@Component({
    selector: 'jhi-quittance-mensuelle-impot-line-delete-dialog',
    templateUrl: './quittance-mensuelle-impot-line-delete-dialog.component.html'
})
export class QuittanceMensuelleImpotLineDeleteDialogComponent {
    quittanceMensuelleImpotLine: IQuittanceMensuelleImpotLine;

    constructor(
        protected quittanceMensuelleImpotLineService: QuittanceMensuelleImpotLineService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quittanceMensuelleImpotLineService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'quittanceMensuelleImpotLineListModification',
                content: 'Deleted an quittanceMensuelleImpotLine'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quittance-mensuelle-impot-line-delete-popup',
    template: ''
})
export class QuittanceMensuelleImpotLineDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ quittanceMensuelleImpotLine }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(QuittanceMensuelleImpotLineDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.quittanceMensuelleImpotLine = quittanceMensuelleImpotLine;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/quittance-mensuelle-impot-line', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/quittance-mensuelle-impot-line', { outlets: { popup: null } }]);
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
