/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ComptaDecisionTestModule } from '../../../test.module';
import { ImpotMensuelClientDeleteDialogComponent } from 'app/entities/impot-mensuel-client/impot-mensuel-client-delete-dialog.component';
import { ImpotMensuelClientService } from 'app/entities/impot-mensuel-client/impot-mensuel-client.service';

describe('Component Tests', () => {
    describe('ImpotMensuelClient Management Delete Component', () => {
        let comp: ImpotMensuelClientDeleteDialogComponent;
        let fixture: ComponentFixture<ImpotMensuelClientDeleteDialogComponent>;
        let service: ImpotMensuelClientService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [ImpotMensuelClientDeleteDialogComponent]
            })
                .overrideTemplate(ImpotMensuelClientDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ImpotMensuelClientDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ImpotMensuelClientService);
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
