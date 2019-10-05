/* tslint:disable max-line-length */
import {ComponentFixture, TestBed} from '@angular/core/testing';
import {ActivatedRoute} from '@angular/router';
import {of} from 'rxjs';

import {ComptaDecisionTestModule} from '../../../test.module';
import {QuittanceMensuelleImpotDetailDetailComponent} from 'app/entities/quittance-mensuelle-impot-detail/quittance-mensuelle-impot-detail-detail.component';
import {QuittanceMensuelleImpotDetail} from 'app/shared/model/quittance-mensuelle-impot-detail.model';

describe('Component Tests', () => {
    describe('QuittanceMensuelleImpotDetail Management Detail Component', () => {
        let comp: QuittanceMensuelleImpotDetailDetailComponent;
        let fixture: ComponentFixture<QuittanceMensuelleImpotDetailDetailComponent>;
        const route = ({ data: of({ quittanceMensuelleImpotDetail: new QuittanceMensuelleImpotDetail(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [QuittanceMensuelleImpotDetailDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(QuittanceMensuelleImpotDetailDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(QuittanceMensuelleImpotDetailDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.quittanceMensuelleImpotDetail).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
