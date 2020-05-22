export interface IOffre {
  id?: number;
  type?: string;
  description?: string;
  image?: string;
}

export class Offre implements IOffre {
  constructor(public id?: number, public type?: string, public description?: string, public image?: string) {}
}
