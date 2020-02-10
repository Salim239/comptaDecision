/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { DeclarationEmployeurAnnuelleUpdateComponent } from 'app/entities/declaration-employeur-annuelle/declaration-employeur-annuelle-update.component';
import { DeclarationEmployeurAnnuelleService } from 'app/entities/declaration-employeur-annuelle/declaration-employeur-annuelle.service';
import { DeclarationEmployeurAnnuelle } from 'app/shared/model/declaration-employeur-annuelle.model';

describe('Component Tests', () => {
    describe('DeclarationEmployeurAnnuelle Management Update Component', () => {
        let comp: DeclarationEmployeurAnnuelleUpdateComponent;
        let fixture: ComponentFixture<DeclarationEmployeurAnnuelleUpdateComponent>;
        let service: DeclarationEmployeurAnnuelleService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [DeclarationEmployeurAnnuelleUpdateComponent]
            })
                .overrideTemplate(DeclarationEmployeurAnnuelleUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(DeclarationEmployeurAnnuelleUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DeclarationEmployeurAnnuelleService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new DeclarationEmployeurAnnuelle(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.declarationEmployeurAnnuelle = entity;
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
                    const entity = new DeclarationEmployeurAnnuelle();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.declarationEmployeurAnnuelle = entity;
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
