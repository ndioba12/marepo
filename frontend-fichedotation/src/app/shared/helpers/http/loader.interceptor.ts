import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import {SharedFacade} from '../../state/shared.facade';
import {finalize} from 'rxjs/operators';
import {Observable} from 'rxjs';

@Injectable()
export class LoaderInterceptor implements HttpInterceptor {
  constructor(public sharedFacade: SharedFacade) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    this.sharedFacade.setLoading(true);
    return next
      .handle(request)
      .pipe(finalize(() => this.sharedFacade.setLoading(false)));
  }
}

export const AppLoaderInterceptor = [
  {
    provide: HTTP_INTERCEPTORS,
    useClass: LoaderInterceptor,
    multi: true,
  },
];
