/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { FicheClientUpdateComponent } from 'app/entities/fiche-client/fiche-client-update.component';
import { FicheClientService } from 'app/entities/fiche-client/fiche-client.service';
import { FicheClient } from 'app/shared/model/fiche-client.model';

describe('Component Tests', () => {
    describe('FicheClient Management Update Component', () => {
        let comp: FicheClientUpdateComponent;
        let fixture: ComponentFixture<FicheClientUpdateComponent>;
        let service: FicheClientService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [FicheClientUpdateComponent]
            })
                .overrideTemplate(FicheClientUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(FicheClientUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(FicheClientService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FicheClient(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.ficheClient = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new FicheClient();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.ficheClient = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
