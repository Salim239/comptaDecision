import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICategorieCnssGerant } from 'app/shared/model/categorie-cnss-gerant.model';
import { CategorieCnssGerantService } from './categorie-cnss-gerant.service';

@Component({
    selector: 'jhi-secteur-activite-delete-dialog',
    templateUrl: './categorie-cnss-gerant-delete-dialog.component.html'
})
export class CategorieCnssGerantDeleteDialogComponent {
    categorieCnssGerant: ICategorieCnssGerant;

    constructor(
        protected categorieCnssGerantService: CategorieCnssGerantService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.categorieCnssGerantService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'categorieCnssGerantListModification',
                content: 'Deleted an categorieCnssGerant'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-secteur-activite-delete-popup',
    template: ''
})
export class categorieCnssGerantDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ categorieCnssGerant }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CategorieCnssGerantDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.categorieCnssGerant = categorieCnssGerant;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/categorie-cnss-gerant', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/categorie-cnss-gerant', { outlets: { popup: null } }]);
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
