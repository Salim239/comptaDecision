/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ComptaDecisionTestModule } from '../../../test.module';
import { QuittanceMensuelleImpotDeleteDialogComponent } from 'app/entities/quittance-mensuelle-impot/quittance-mensuelle-impot-delete-dialog.component';
import { QuittanceMensuelleImpotService } from 'app/entities/quittance-mensuelle-impot/quittance-mensuelle-impot.service';

describe('Component Tests', () => {
    describe('QuittanceMensuelleImpot Management Delete Component', () => {
        let comp: QuittanceMensuelleImpotDeleteDialogComponent;
        let fixture: ComponentFixture<QuittanceMensuelleImpotDeleteDialogComponent>;
        let service: QuittanceMensuelleImpotService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [QuittanceMensuelleImpotDeleteDialogComponent]
            })
                .overrideTemplate(QuittanceMensuelleImpotDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(QuittanceMensuelleImpotDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(QuittanceMensuelleImpotService);
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
