import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IActualite, Actualite } from 'app/shared/model/actualite.model';
import { ActualiteService } from './actualite.service';
import { ActualiteComponent } from './actualite.component';
import { ActualiteDetailComponent } from './actualite-detail.component';
import { ActualiteUpdateComponent } from './actualite-update.component';

@Injectable({ providedIn: 'root' })
export class ActualiteResolve implements Resolve<IActualite> {
  constructor(private service: ActualiteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IActualite> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((actualite: HttpResponse<Actualite>) => {
          if (actualite.body) {
            return of(actualite.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Actualite());
  }
}

export const actualiteRoute: Routes = [
  {
    path: '',
    component: ActualiteComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'Actualites',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ActualiteDetailComponent,
    resolve: {
      actualite: ActualiteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Actualites',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ActualiteUpdateComponent,
    resolve: {
      actualite: ActualiteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Actualites',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ActualiteUpdateComponent,
    resolve: {
      actualite: ActualiteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Actualites',
    },
    canActivate: [UserRouteAccessService],
  },
];
