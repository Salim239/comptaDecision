import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager} from 'ng-jhipster';

import {IApplicationUserFicheClient} from 'app/shared/model/application-user-fiche-client.model';
import {ApplicationUserFicheClientService} from './application-user-fiche-client.service';

@Component({
    selector: 'jhi-application-user-fiche-client-delete-dialog',
    templateUrl: './application-user-fiche-client-delete-dialog.component.html'
})
export class ApplicationUserFicheClientDeleteDialogComponent {
    applicationUserFicheClient: IApplicationUserFicheClient;

    constructor(
        protected applicationUserFicheClientService: ApplicationUserFicheClientService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.applicationUserFicheClientService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'applicationUserFicheClientListModification',
                content: 'Deleted an applicationUserFicheClient'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-application-user-fiche-client-delete-popup',
    template: ''
})
export class ApplicationUserFicheClientDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ applicationUserFicheClient }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ApplicationUserFicheClientDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.applicationUserFicheClient = applicationUserFicheClient;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/application-user-fiche-client', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/application-user-fiche-client', { outlets: { popup: null } }]);
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
