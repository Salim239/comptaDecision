/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { CnssDetailComponent } from 'app/entities/cnss/cnss-detail.component';
import { Cnss } from 'app/shared/model/cnss.model';

describe('Component Tests', () => {
    describe('Cnss Management Detail Component', () => {
        let comp: CnssDetailComponent;
        let fixture: ComponentFixture<CnssDetailComponent>;
        const route = ({ data: of({ cnss: new Cnss(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [CnssDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(CnssDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CnssDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.cnss).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
