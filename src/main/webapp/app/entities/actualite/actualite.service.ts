import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IActualite } from 'app/shared/model/actualite.model';

type EntityResponseType = HttpResponse<IActualite>;
type EntityArrayResponseType = HttpResponse<IActualite[]>;

@Injectable({ providedIn: 'root' })
export class ActualiteService {
  public resourceUrl = SERVER_API_URL + 'api/actualites';

  constructor(protected http: HttpClient) {}

  create(actualite: IActualite): Observable<EntityResponseType> {
    return this.http.post<IActualite>(this.resourceUrl, actualite, { observe: 'response' });
  }

  update(actualite: IActualite): Observable<EntityResponseType> {
    return this.http.put<IActualite>(this.resourceUrl, actualite, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IActualite>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IActualite[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
