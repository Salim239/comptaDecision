/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { CnssUpdateComponent } from 'app/entities/cnss/cnss-update.component';
import { CnssService } from 'app/entities/cnss/cnss.service';
import { Cnss } from 'app/shared/model/cnss.model';

describe('Component Tests', () => {
    describe('Cnss Management Update Component', () => {
        let comp: CnssUpdateComponent;
        let fixture: ComponentFixture<CnssUpdateComponent>;
        let service: CnssService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [CnssUpdateComponent]
            })
                .overrideTemplate(CnssUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CnssUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CnssService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Cnss(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.cnss = entity;
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
                    const entity = new Cnss();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.cnss = entity;
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
