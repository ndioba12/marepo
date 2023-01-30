import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {catchError, map} from 'rxjs/operators';
import {throwError} from 'rxjs';
import {AuthService} from '../../../modules/authentification/services/auth.service';
import {AuthResponseData} from '../../../modules/authentification/models/auth-response-data';
import {Router} from '@angular/router';
import {CredentialsService} from '../../../modules/authentification/services/credentials.service';
import {AlertService} from '../others/alert.service';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(
    private credentialsService: CredentialsService,
    private alertService: AlertService,
    private authService: AuthService,
    private route: Router
  ) {
  }

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler): any {
    let authReq = req;
    const token = this.credentialsService.undecodeAccessToken;
    if (!this.route.url.includes('auth') && !this.route.url.includes('express') && token != null) {
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token)});
    }
    return next.handle(authReq).pipe(
      map((event: HttpEvent<any>) => {
        if (event instanceof HttpResponse && event.headers.get('refreshToken')) {
          const header = event.headers.get('refreshToken') ?? null;
          if (header != null) {
            const response: AuthResponseData = JSON.parse(header);
            this.authService.refreshUserCredentials(response);
          }
        }
        return event;
      }), catchError((err) => {
        switch (err?.status) {
          case 401:
            this.authService.logout(true);
            this.alertService.showAlert({
              status: 'BAD_REQUEST',
              titre: 'Authentification',
              message: 'Votre session a expiré, veuillez vous reconnecter !'
            });
            location.reload();

            break;
          case 403:
            this.alertService.showAlert({
              status: 'BAD_REQUEST',
              titre: 'Authentification',
              message: 'Vous n\'avez pas les droits nécessaires pour effectuer cette action !'
            });
            break;
          case 404:
            this.alertService.showAlert({
              status: 'NOT_FOUND',
              titre: 'Serveur',
              message: 'La ressource demandée est indisponible !'
            });
            break;
          default:
            this.alertService.showAlert({
              status: 'EXCEPTION',
              message: 'Le serveur est actuellement indisponible !',
              titre: 'Serveur'
            });
            break;
        }
        const error = err.error.message || err.statusText;
        return throwError(() => error);
      })
    );
  }
}

export const httpInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
];
