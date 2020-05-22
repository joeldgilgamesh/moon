import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MoonTestModule } from '../../../test.module';
import { ActualiteUpdateComponent } from 'app/entities/actualite/actualite-update.component';
import { ActualiteService } from 'app/entities/actualite/actualite.service';
import { Actualite } from 'app/shared/model/actualite.model';

describe('Component Tests', () => {
  describe('Actualite Management Update Component', () => {
    let comp: ActualiteUpdateComponent;
    let fixture: ComponentFixture<ActualiteUpdateComponent>;
    let service: ActualiteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MoonTestModule],
        declarations: [ActualiteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ActualiteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ActualiteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ActualiteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Actualite(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Actualite();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
