import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeclarationEmployeurAnnuelle } from 'app/shared/model/declaration-employeur-annuelle.model';
import { DeclarationEmployeurAnnuelleService } from './declaration-employeur-annuelle.service';

@Component({
    selector: 'jhi-declaration-employeur-annuelle-delete-dialog',
    templateUrl: './declaration-employeur-annuelle-delete-dialog.component.html'
})
export class DeclarationEmployeurAnnuelleDeleteDialogComponent {
    declarationEmployeurAnnuelle: IDeclarationEmployeurAnnuelle;

    constructor(
        protected declarationEmployeurAnnuelleService: DeclarationEmployeurAnnuelleService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.declarationEmployeurAnnuelleService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'declarationEmployeurAnnuelleListModification',
                content: 'Deleted an declarationEmployeurAnnuelle'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-declaration-employeur-annuelle-delete-popup',
    template: ''
})
export class DeclarationEmployeurAnnuelleDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ declarationEmployeurAnnuelle }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DeclarationEmployeurAnnuelleDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.declarationEmployeurAnnuelle = declarationEmployeurAnnuelle;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/declaration-employeur-annuelle', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/declaration-employeur-annuelle', { outlets: { popup: null } }]);
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
