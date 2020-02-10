/* tslint:disable max-line-length */
import {ComponentFixture, fakeAsync, inject, TestBed, tick} from '@angular/core/testing';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
import {of} from 'rxjs';
import {JhiEventManager} from 'ng-jhipster';

import {ComptaDecisionTestModule} from '../../../test.module';
import {QuittanceMensuelleImpotDetailDeleteDialogComponent} from 'app/entities/quittance-mensuelle-impot-detail/quittance-mensuelle-impot-detail-delete-dialog.component';
import {QuittanceMensuelleImpotDetailService} from 'app/entities/quittance-mensuelle-impot-detail/quittance-mensuelle-impot-detail.service';

describe('Component Tests', () => {
    describe('QuittanceMensuelleImpotDetail Management Delete Component', () => {
        let comp: QuittanceMensuelleImpotDetailDeleteDialogComponent;
        let fixture: ComponentFixture<QuittanceMensuelleImpotDetailDeleteDialogComponent>;
        let service: QuittanceMensuelleImpotDetailService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [QuittanceMensuelleImpotDetailDeleteDialogComponent]
            })
                .overrideTemplate(QuittanceMensuelleImpotDetailDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(QuittanceMensuelleImpotDetailDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(QuittanceMensuelleImpotDetailService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
