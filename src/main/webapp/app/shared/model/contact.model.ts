export interface IContact {
  id?: number;
  nom?: string;
  prenom?: string;
  email?: string;
  region?: string;
  typeService?: string;
  descriptionRequete?: string;
}

export class Contact implements IContact {
  constructor(
    public id?: number,
    public nom?: string,
    public prenom?: string,
    public email?: string,
    public region?: string,
    public typeService?: string,
    public descriptionRequete?: string
  ) {}
}
