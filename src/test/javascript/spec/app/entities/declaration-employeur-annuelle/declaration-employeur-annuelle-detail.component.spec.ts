/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { DeclarationEmployeurAnnuelleDetailComponent } from 'app/entities/declaration-employeur-annuelle/declaration-employeur-annuelle-detail.component';
import { DeclarationEmployeurAnnuelle } from 'app/shared/model/declaration-employeur-annuelle.model';

describe('Component Tests', () => {
    describe('DeclarationEmployeurAnnuelle Management Detail Component', () => {
        let comp: DeclarationEmployeurAnnuelleDetailComponent;
        let fixture: ComponentFixture<DeclarationEmployeurAnnuelleDetailComponent>;
        const route = ({ data: of({ declarationEmployeurAnnuelle: new DeclarationEmployeurAnnuelle(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [DeclarationEmployeurAnnuelleDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DeclarationEmployeurAnnuelleDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DeclarationEmployeurAnnuelleDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.declarationEmployeurAnnuelle).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
