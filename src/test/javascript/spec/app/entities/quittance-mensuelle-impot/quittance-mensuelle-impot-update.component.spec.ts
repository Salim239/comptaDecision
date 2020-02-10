/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { QuittanceMensuelleImpotUpdateComponent } from 'app/entities/quittance-mensuelle-impot/quittance-mensuelle-impot-update.component';
import { QuittanceMensuelleImpotService } from 'app/entities/quittance-mensuelle-impot/quittance-mensuelle-impot.service';
import { QuittanceMensuelleImpot } from 'app/shared/model/quittance-mensuelle-impot.model';

describe('Component Tests', () => {
    describe('QuittanceMensuelleImpot Management Update Component', () => {
        let comp: QuittanceMensuelleImpotUpdateComponent;
        let fixture: ComponentFixture<QuittanceMensuelleImpotUpdateComponent>;
        let service: QuittanceMensuelleImpotService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [QuittanceMensuelleImpotUpdateComponent]
            })
                .overrideTemplate(QuittanceMensuelleImpotUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(QuittanceMensuelleImpotUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(QuittanceMensuelleImpotService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new QuittanceMensuelleImpot(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.quittanceMensuelleImpot = entity;
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
                    const entity = new QuittanceMensuelleImpot();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.quittanceMensuelleImpot = entity;
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
