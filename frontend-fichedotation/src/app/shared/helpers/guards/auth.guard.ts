import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {CredentialsService} from '../../../modules/authentification/services/credentials.service';
import { AlertService } from '../others/alert.service';
import {Logger} from '../others/logger.service';

const log = new Logger('AuthGuard');

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private _credentialsService: CredentialsService,
    private _alertService: AlertService
    ) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    if (!this._credentialsService.isExpiredToken()) {
      return true;
    } else {

      log.debug('Not authenticated, redirecting and adding redirect url...');

      this._alertService.showAlert({
        titre: "Authentification",
        status: "Erreur",
        message: "Vous n'êtes pas connecté, redirection vers la page de connexion !"
      })

      this.router.navigate(['/auth/login'], {queryParams: {redirect: state.url}, replaceUrl: true});
      return false;
    }
  }

}
