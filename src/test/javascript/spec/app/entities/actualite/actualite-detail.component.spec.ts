import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MoonTestModule } from '../../../test.module';
import { ActualiteDetailComponent } from 'app/entities/actualite/actualite-detail.component';
import { Actualite } from 'app/shared/model/actualite.model';

describe('Component Tests', () => {
  describe('Actualite Management Detail Component', () => {
    let comp: ActualiteDetailComponent;
    let fixture: ComponentFixture<ActualiteDetailComponent>;
    const route = ({ data: of({ actualite: new Actualite(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MoonTestModule],
        declarations: [ActualiteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ActualiteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ActualiteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load actualite on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.actualite).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
