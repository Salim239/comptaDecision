import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFicheClient } from 'app/shared/model/fiche-client.model';
import { FicheClientService } from './fiche-client.service';

@Component({
    selector: 'jhi-fiche-client-delete-dialog',
    templateUrl: './fiche-client-delete-dialog.component.html'
})
export class FicheClientDeleteDialogComponent {
    ficheClient: IFicheClient;

    constructor(
        protected ficheClientService: FicheClientService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.ficheClientService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'ficheClientListModification',
                content: 'Deleted an ficheClient'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-fiche-client-delete-popup',
    template: ''
})
export class FicheClientDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ficheClient }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(FicheClientDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.ficheClient = ficheClient;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/fiche-client', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/fiche-client', { outlets: { popup: null } }]);
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
