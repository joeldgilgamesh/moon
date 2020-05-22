import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IContact, Contact } from 'app/shared/model/contact.model';
import { ContactService } from './contact.service';

@Component({
  selector: 'jhi-contact-update',
  templateUrl: './contact-update.component.html',
})
export class ContactUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [],
    prenom: [],
    email: [],
    region: [],
    typeService: [],
    descriptionRequete: [],
  });

  constructor(protected contactService: ContactService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ contact }) => {
      this.updateForm(contact);
    });
  }

  updateForm(contact: IContact): void {
    this.editForm.patchValue({
      id: contact.id,
      nom: contact.nom,
      prenom: contact.prenom,
      email: contact.email,
      region: contact.region,
      typeService: contact.typeService,
      descriptionRequete: contact.descriptionRequete,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const contact = this.createFromForm();
    if (contact.id !== undefined) {
      this.subscribeToSaveResponse(this.contactService.update(contact));
    } else {
      this.subscribeToSaveResponse(this.contactService.create(contact));
    }
  }

  private createFromForm(): IContact {
    return {
      ...new Contact(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      email: this.editForm.get(['email'])!.value,
      region: this.editForm.get(['region'])!.value,
      typeService: this.editForm.get(['typeService'])!.value,
      descriptionRequete: this.editForm.get(['descriptionRequete'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IContact>>): void {
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
