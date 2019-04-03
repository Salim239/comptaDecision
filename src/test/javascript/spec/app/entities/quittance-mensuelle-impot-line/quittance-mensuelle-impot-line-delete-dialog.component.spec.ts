/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ComptaDecisionTestModule } from '../../../test.module';
import { QuittanceMensuelleImpotLineDeleteDialogComponent } from 'app/entities/quittance-mensuelle-impot-line/quittance-mensuelle-impot-line-delete-dialog.component';
import { QuittanceMensuelleImpotLineService } from 'app/entities/quittance-mensuelle-impot-line/quittance-mensuelle-impot-line.service';

describe('Component Tests', () => {
    describe('QuittanceMensuelleImpotLine Management Delete Component', () => {
        let comp: QuittanceMensuelleImpotLineDeleteDialogComponent;
        let fixture: ComponentFixture<QuittanceMensuelleImpotLineDeleteDialogComponent>;
        let service: QuittanceMensuelleImpotLineService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [QuittanceMensuelleImpotLineDeleteDialogComponent]
            })
                .overrideTemplate(QuittanceMensuelleImpotLineDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(QuittanceMensuelleImpotLineDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(QuittanceMensuelleImpotLineService);
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
