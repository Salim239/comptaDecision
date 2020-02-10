import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IImpotMensuelClient } from 'app/shared/model/impot-mensuel-client.model';
import { ImpotMensuelClientService } from './impot-mensuel-client.service';

@Component({
    selector: 'jhi-impot-mensuel-client-delete-dialog',
    templateUrl: './impot-mensuel-client-delete-dialog.component.html'
})
export class ImpotMensuelClientDeleteDialogComponent {
    impotMensuelClient: IImpotMensuelClient;

    constructor(
        protected impotMensuelClientService: ImpotMensuelClientService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.impotMensuelClientService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'impotMensuelClientListModification',
                content: 'Deleted an impotMensuelClient'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-impot-mensuel-client-delete-popup',
    template: ''
})
export class ImpotMensuelClientDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ impotMensuelClient }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ImpotMensuelClientDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.impotMensuelClient = impotMensuelClient;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/impot-mensuel-client', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/impot-mensuel-client', { outlets: { popup: null } }]);
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
