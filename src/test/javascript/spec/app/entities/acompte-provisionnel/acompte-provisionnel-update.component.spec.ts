/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ComptaDecisionTestModule } from '../../../test.module';
import { AcompteProvisionnelUpdateComponent } from 'app/entities/acompte-provisionnel/acompte-provisionnel-update.component';
import { AcompteProvisionnelService } from 'app/entities/acompte-provisionnel/acompte-provisionnel.service';
import { AcompteProvisionnel } from 'app/shared/model/acompte-provisionnel.model';

describe('Component Tests', () => {
    describe('AcompteProvisionnel Management Update Component', () => {
        let comp: AcompteProvisionnelUpdateComponent;
        let fixture: ComponentFixture<AcompteProvisionnelUpdateComponent>;
        let service: AcompteProvisionnelService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ComptaDecisionTestModule],
                declarations: [AcompteProvisionnelUpdateComponent]
            })
                .overrideTemplate(AcompteProvisionnelUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AcompteProvisionnelUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AcompteProvisionnelService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new AcompteProvisionnel(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.acompteProvisionnel = entity;
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
                    const entity = new AcompteProvisionnel();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.acompteProvisionnel = entity;
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
