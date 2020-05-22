import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IOffre, Offre } from 'app/shared/model/offre.model';
import { OffreService } from './offre.service';

@Component({
  selector: 'jhi-offre-update',
  templateUrl: './offre-update.component.html',
})
export class OffreUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    type: [],
    description: [],
    image: [],
  });

  constructor(protected offreService: OffreService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ offre }) => {
      this.updateForm(offre);
    });
  }

  updateForm(offre: IOffre): void {
    this.editForm.patchValue({
      id: offre.id,
      type: offre.type,
      description: offre.description,
      image: offre.image,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const offre = this.createFromForm();
    if (offre.id !== undefined) {
      this.subscribeToSaveResponse(this.offreService.update(offre));
    } else {
      this.subscribeToSaveResponse(this.offreService.create(offre));
    }
  }

  private createFromForm(): IOffre {
    return {
      ...new Offre(),
      id: this.editForm.get(['id'])!.value,
      type: this.editForm.get(['type'])!.value,
      description: this.editForm.get(['description'])!.value,
      image: this.editForm.get(['image'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IOffre>>): void {
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
