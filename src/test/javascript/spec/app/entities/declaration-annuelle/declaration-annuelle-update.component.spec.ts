/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { DeclarationAnnuelleUpdateComponent } from 'app/entities/declaration-annuelle/declaration-annuelle-update.component';
import { DeclarationAnnuelleService } from 'app/entities/declaration-annuelle/declaration-annuelle.service';
import { DeclarationAnnuelle } from 'app/shared/model/declaration-annuelle.model';

describe('Component Tests', () => {
    describe('DeclarationAnnuelle Management Update Component', () => {
        let comp: DeclarationAnnuelleUpdateComponent;
        let fixture: ComponentFixture<DeclarationAnnuelleUpdateComponent>;
        let service: DeclarationAnnuelleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [DeclarationAnnuelleUpdateComponent]
            })
                .overrideTemplate(DeclarationAnnuelleUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DeclarationAnnuelleUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DeclarationAnnuelleService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DeclarationAnnuelle(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.declarationAnnuelle = entity;
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
                    const entity = new DeclarationAnnuelle();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.declarationAnnuelle = entity;
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
