import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router} from '@angular/router';
import {CredentialsService} from '../../../modules/authentification/services/credentials.service';
import {AlertService} from '../others/alert.service';


@Injectable()
export class RoleGuard implements CanActivate {
  constructor(
    private credentialsService: CredentialsService,
    private alertService: AlertService,
    private router: Router
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const authorizedRoles = route?.data?.role;
    const userRoles = this.credentialsService.authorities;

    if (authorizedRoles.includes('*')) {
      return true;
    }
    if (authorizedRoles.includes(userRoles[0])) {
      return true;
    } else {
      this.alertService.showAlert({
        status: 'ACCESS_DENIED',
        message: 'Vous n\'avez pas la permission d\'accéder à cette ressource !',
        titre: 'Autorisations'
      });

      this.router.navigateByUrl('/dashboard');
    }
  }
}
