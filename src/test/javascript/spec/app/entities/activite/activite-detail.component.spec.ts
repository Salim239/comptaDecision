/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { ActiviteDetailComponent } from 'app/entities/activite/activite-detail.component';
import { Activite } from 'app/shared/model/activite.model';

describe('Component Tests', () => {
    describe('Activite Management Detail Component', () => {
        let comp: ActiviteDetailComponent;
        let fixture: ComponentFixture<ActiviteDetailComponent>;
        const route = ({ data: of({ activite: new Activite(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [ActiviteDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ActiviteDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ActiviteDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.activite).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
