import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISecteurActivite } from 'app/shared/model/secteur-activite.model';
import { SecteurActiviteService } from './secteur-activite.service';

@Component({
    selector: 'jhi-secteur-activite-delete-dialog',
    templateUrl: './secteur-activite-delete-dialog.component.html'
})
export class SecteurActiviteDeleteDialogComponent {
    secteurActivite: ISecteurActivite;

    constructor(
        protected secteurActiviteService: SecteurActiviteService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.secteurActiviteService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'secteurActiviteListModification',
                content: 'Deleted an secteurActivite'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-secteur-activite-delete-popup',
    template: ''
})
export class SecteurActiviteDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ secteurActivite }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(SecteurActiviteDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.secteurActivite = secteurActivite;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/secteur-activite', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/secteur-activite', { outlets: { popup: null } }]);
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
