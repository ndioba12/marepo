import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {AuthService} from '../../../modules/authentification/services/auth.service';
import {CredentialsService} from '../../../modules/authentification/services/credentials.service';
import {Logger} from '../others/logger.service';

const log = new Logger('AuthGuard');

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private credentialsService: CredentialsService,
    private authService: AuthService) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    if (!this.credentialsService.isExpiredToken()) {
      return true;
    } else {
      log.debug('Not authenticated, redirecting and adding redirect url...');
      this.router.navigate(['/auth/login'], {queryParams: {redirect: state.url}, replaceUrl: true});
      return false;
    }
  }

}
