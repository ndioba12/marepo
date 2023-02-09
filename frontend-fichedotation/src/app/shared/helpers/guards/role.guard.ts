import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router} from '@angular/router';
import {CredentialsService} from '../../../modules/authentification/services/credentials.service';
import { AlertService } from '../others/alert.service';


@Injectable()
export class RoleGuard implements CanActivate {
  constructor(
    private credentialsService: CredentialsService,
    private alertService: AlertService,
    private router: Router
  ) {
  }

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const authorizedRoles = route?.data?.['role'];
    const userRoles = this.credentialsService.userAuthorities;

    if (authorizedRoles.includes('*')) {
      return true;
    }
    else if (authorizedRoles.includes(userRoles[0]?.['authority'])) {
      return true;
    } else {
      this.alertService.showAlert({
        status: 'ACCESS_DENIED',
        message: 'Vous n\'avez pas la permission d\'accéder à cette ressource !',
        titre: 'Autorisations'
      });

      this.router.navigateByUrl('/dashboard');

    // Solution de l'erreur suivante:
    // " Error:️ Function lacks ending return statement
    // and return type does not include 'undefined' "
      return false;
    }
  }
}
