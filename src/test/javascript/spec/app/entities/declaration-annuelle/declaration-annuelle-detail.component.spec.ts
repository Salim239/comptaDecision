/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { DeclarationAnnuelleDetailComponent } from 'app/entities/declaration-annuelle/declaration-annuelle-detail.component';
import { DeclarationAnnuelle } from 'app/shared/model/declaration-annuelle.model';

describe('Component Tests', () => {
    describe('DeclarationAnnuelle Management Detail Component', () => {
        let comp: DeclarationAnnuelleDetailComponent;
        let fixture: ComponentFixture<DeclarationAnnuelleDetailComponent>;
        const route = ({ data: of({ declarationAnnuelle: new DeclarationAnnuelle(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [DeclarationAnnuelleDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DeclarationAnnuelleDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DeclarationAnnuelleDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.declarationAnnuelle).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
