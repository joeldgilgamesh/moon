export interface IActualite {
  id?: number;
  titre?: string;
  description?: string;
  datePublication?: string;
}

export class Actualite implements IActualite {
  constructor(public id?: number, public titre?: string, public description?: string, public datePublication?: string) {}
}
