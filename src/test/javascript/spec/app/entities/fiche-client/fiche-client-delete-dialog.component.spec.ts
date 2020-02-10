/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ComptaDecisionTestModule } from '../../../test.module';
import { FicheClientDeleteDialogComponent } from 'app/entities/fiche-client/fiche-client-delete-dialog.component';
import { FicheClientService } from 'app/entities/fiche-client/fiche-client.service';

describe('Component Tests', () => {
    describe('FicheClient Management Delete Component', () => {
        let comp: FicheClientDeleteDialogComponent;
        let fixture: ComponentFixture<FicheClientDeleteDialogComponent>;
        let service: FicheClientService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [FicheClientDeleteDialogComponent]
            })
                .overrideTemplate(FicheClientDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(FicheClientDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FicheClientService);
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
