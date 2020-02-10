/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ComptaDecisionTestModule } from '../../../test.module';
import { DeclarationEmployeurAnnuelleDeleteDialogComponent } from 'app/entities/declaration-employeur-annuelle/declaration-employeur-annuelle-delete-dialog.component';
import { DeclarationEmployeurAnnuelleService } from 'app/entities/declaration-employeur-annuelle/declaration-employeur-annuelle.service';

describe('Component Tests', () => {
    describe('DeclarationEmployeurAnnuelle Management Delete Component', () => {
        let comp: DeclarationEmployeurAnnuelleDeleteDialogComponent;
        let fixture: ComponentFixture<DeclarationEmployeurAnnuelleDeleteDialogComponent>;
        let service: DeclarationEmployeurAnnuelleService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [DeclarationEmployeurAnnuelleDeleteDialogComponent]
            })
                .overrideTemplate(DeclarationEmployeurAnnuelleDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DeclarationEmployeurAnnuelleDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DeclarationEmployeurAnnuelleService);
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
