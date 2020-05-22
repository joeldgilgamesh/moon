import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IActualite, Actualite } from 'app/shared/model/actualite.model';
import { ActualiteService } from './actualite.service';

@Component({
  selector: 'jhi-actualite-update',
  templateUrl: './actualite-update.component.html',
})
export class ActualiteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    titre: [],
    description: [],
    datePublication: [],
  });

  constructor(protected actualiteService: ActualiteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ actualite }) => {
      this.updateForm(actualite);
    });
  }

  updateForm(actualite: IActualite): void {
    this.editForm.patchValue({
      id: actualite.id,
      titre: actualite.titre,
      description: actualite.description,
      datePublication: actualite.datePublication,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const actualite = this.createFromForm();
    if (actualite.id !== undefined) {
      this.subscribeToSaveResponse(this.actualiteService.update(actualite));
    } else {
      this.subscribeToSaveResponse(this.actualiteService.create(actualite));
    }
  }

  private createFromForm(): IActualite {
    return {
      ...new Actualite(),
      id: this.editForm.get(['id'])!.value,
      titre: this.editForm.get(['titre'])!.value,
      description: this.editForm.get(['description'])!.value,
      datePublication: this.editForm.get(['datePublication'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IActualite>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
