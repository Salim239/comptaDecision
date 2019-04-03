/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { QuittanceMensuelleImpotLineUpdateComponent } from 'app/entities/quittance-mensuelle-impot-line/quittance-mensuelle-impot-line-update.component';
import { QuittanceMensuelleImpotLineService } from 'app/entities/quittance-mensuelle-impot-line/quittance-mensuelle-impot-line.service';
import { QuittanceMensuelleImpotLine } from 'app/shared/model/quittance-mensuelle-impot-line.model';

describe('Component Tests', () => {
    describe('QuittanceMensuelleImpotLine Management Update Component', () => {
        let comp: QuittanceMensuelleImpotLineUpdateComponent;
        let fixture: ComponentFixture<QuittanceMensuelleImpotLineUpdateComponent>;
        let service: QuittanceMensuelleImpotLineService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [QuittanceMensuelleImpotLineUpdateComponent]
            })
                .overrideTemplate(QuittanceMensuelleImpotLineUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(QuittanceMensuelleImpotLineUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(QuittanceMensuelleImpotLineService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new QuittanceMensuelleImpotLine(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.quittanceMensuelleImpotLine = entity;
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
                    const entity = new QuittanceMensuelleImpotLine();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.quittanceMensuelleImpotLine = entity;
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
