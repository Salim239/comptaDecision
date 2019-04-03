/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ComptaDecisionTestModule } from '../../../test.module';
import { AcompteProvisionnelDeleteDialogComponent } from 'app/entities/acompte-provisionnel/acompte-provisionnel-delete-dialog.component';
import { AcompteProvisionnelService } from 'app/entities/acompte-provisionnel/acompte-provisionnel.service';

describe('Component Tests', () => {
    describe('AcompteProvisionnel Management Delete Component', () => {
        let comp: AcompteProvisionnelDeleteDialogComponent;
        let fixture: ComponentFixture<AcompteProvisionnelDeleteDialogComponent>;
        let service: AcompteProvisionnelService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [AcompteProvisionnelDeleteDialogComponent]
            })
                .overrideTemplate(AcompteProvisionnelDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AcompteProvisionnelDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AcompteProvisionnelService);
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
