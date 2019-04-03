/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { AcompteProvisionnelDetailComponent } from 'app/entities/acompte-provisionnel/acompte-provisionnel-detail.component';
import { AcompteProvisionnel } from 'app/shared/model/acompte-provisionnel.model';

describe('Component Tests', () => {
    describe('AcompteProvisionnel Management Detail Component', () => {
        let comp: AcompteProvisionnelDetailComponent;
        let fixture: ComponentFixture<AcompteProvisionnelDetailComponent>;
        const route = ({ data: of({ acompteProvisionnel: new AcompteProvisionnel(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [AcompteProvisionnelDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AcompteProvisionnelDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AcompteProvisionnelDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.acompteProvisionnel).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
