/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ComptaDecisionTestModule } from '../../../test.module';
import { ImpotMensuelDeleteDialogComponent } from 'app/entities/impot-mensuel/impot-mensuel-delete-dialog.component';
import { ImpotMensuelService } from 'app/entities/impot-mensuel/impot-mensuel.service';

describe('Component Tests', () => {
    describe('ImpotMensuel Management Delete Component', () => {
        let comp: ImpotMensuelDeleteDialogComponent;
        let fixture: ComponentFixture<ImpotMensuelDeleteDialogComponent>;
        let service: ImpotMensuelService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [ImpotMensuelDeleteDialogComponent]
            })
                .overrideTemplate(ImpotMensuelDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImpotMensuelDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImpotMensuelService);
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
