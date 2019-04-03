import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IImpotMensuel } from 'app/shared/model/impot-mensuel.model';
import { ImpotMensuelService } from './impot-mensuel.service';

@Component({
    selector: 'jhi-impot-mensuel-delete-dialog',
    templateUrl: './impot-mensuel-delete-dialog.component.html'
})
export class ImpotMensuelDeleteDialogComponent {
    impotMensuel: IImpotMensuel;

    constructor(
        protected impotMensuelService: ImpotMensuelService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.impotMensuelService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'impotMensuelListModification',
                content: 'Deleted an impotMensuel'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-impot-mensuel-delete-popup',
    template: ''
})
export class ImpotMensuelDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ impotMensuel }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ImpotMensuelDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.impotMensuel = impotMensuel;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/impot-mensuel', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/impot-mensuel', { outlets: { popup: null } }]);
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
