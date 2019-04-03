import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDeclarationAnnuelle } from 'app/shared/model/declaration-annuelle.model';
import { DeclarationAnnuelleService } from './declaration-annuelle.service';

@Component({
    selector: 'jhi-declaration-annuelle-delete-dialog',
    templateUrl: './declaration-annuelle-delete-dialog.component.html'
})
export class DeclarationAnnuelleDeleteDialogComponent {
    declarationAnnuelle: IDeclarationAnnuelle;

    constructor(
        protected declarationAnnuelleService: DeclarationAnnuelleService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.declarationAnnuelleService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'declarationAnnuelleListModification',
                content: 'Deleted an declarationAnnuelle'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-declaration-annuelle-delete-popup',
    template: ''
})
export class DeclarationAnnuelleDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ declarationAnnuelle }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DeclarationAnnuelleDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.declarationAnnuelle = declarationAnnuelle;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/declaration-annuelle', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/declaration-annuelle', { outlets: { popup: null } }]);
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
