/* tslint:disable max-line-length */
import {ComponentFixture, fakeAsync, TestBed, tick} from '@angular/core/testing';
import {HttpResponse} from '@angular/common/http';
import {of} from 'rxjs';

import {ComptaDecisionTestModule} from '../../../test.module';
import {QuittanceMensuelleImpotDetailUpdateComponent} from 'app/entities/quittance-mensuelle-impot-detail/quittance-mensuelle-impot-detail-update.component';
import {QuittanceMensuelleImpotDetailService} from 'app/entities/quittance-mensuelle-impot-detail/quittance-mensuelle-impot-detail.service';
import {QuittanceMensuelleImpotDetail} from 'app/shared/model/quittance-mensuelle-impot-detail.model';

describe('Component Tests', () => {
    describe('QuittanceMensuelleImpotDetail Management Update Component', () => {
        let comp: QuittanceMensuelleImpotDetailUpdateComponent;
        let fixture: ComponentFixture<QuittanceMensuelleImpotDetailUpdateComponent>;
        let service: QuittanceMensuelleImpotDetailService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [QuittanceMensuelleImpotDetailUpdateComponent]
            })
                .overrideTemplate(QuittanceMensuelleImpotDetailUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(QuittanceMensuelleImpotDetailUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(QuittanceMensuelleImpotDetailService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new QuittanceMensuelleImpotDetail(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.quittanceMensuelleImpotDetail = entity;
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
                    const entity = new QuittanceMensuelleImpotDetail();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.quittanceMensuelleImpotDetail = entity;
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
