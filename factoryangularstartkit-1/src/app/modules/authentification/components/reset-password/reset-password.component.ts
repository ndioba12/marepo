import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CustomPasswordValidators} from '../../../../@shared/helpers/validators/password.validators';
import {AuthService} from '../../services/auth.service';
import {CredentialsService} from '../../services/credentials.service';
import {AlertService} from '../../../../@shared/helpers/others/alert.service';
import jwt_decode from 'jwt-decode';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  hide = true;
  hideConfirmPassword = true;
  action: string = '';
  tokenInfos: any;
  loginForm: FormGroup = this.formBuilder.group({
    login: ['', [Validators.required, Validators.email]],
    newPassword: ['', Validators.compose([
      Validators.required,
      Validators.minLength(8),
      CustomPasswordValidators.patternValidator(/\d/, {
        hasNumber: true
      }),
      // check whether the entered password has upper case letter
      CustomPasswordValidators.patternValidator(/[A-Z]/, {
        hasCapitalCase: true
      }),
      // check whether the entered password has a lower case letter
      CustomPasswordValidators.patternValidator(/[a-z]/, {
        hasSmallCase: true
      }),
      // check whether the entered password has a special character
      CustomPasswordValidators.patternValidator(
        /[ !@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/,
        {
          hasSpecialCharacters: true
        }
      )]
    )],
    newPasswordConfirm: ['', Validators.required],
  }, {validator: CustomPasswordValidators.PasswordValidator});

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private activatedRoute: ActivatedRoute,
    private authService: AuthService,
    private credentialsService: CredentialsService,
    private alertService: AlertService
  ) {
  }

  get f() {
    return this.loginForm.controls;
  }

  // On SignIn link click
  onSignIn() {
    this.router.navigate(['sign-in'], {relativeTo: this.route.parent});
  }

  // On Forgotpassword link click
  onForgotpassword() {
    this.router.navigate(['forgot-password'], {relativeTo: this.route.parent});
  }

  // On Signup link click
  onSignup() {
    this.router.navigate(['sign-up'], {relativeTo: this.route.parent});
  }

  onResetPassword() {
    if (this.loginForm.invalid) {
      this.checkConstraintsValidation();
      if (this.loginForm?.errors?.misMatch && !this.f.newPasswordConfirm?.errors  && !this.f.newPassword?.errors)
        this.alertService.showAlert({status: "ERROR", message: "Les deux mot de passe ne correspondent pas !", titre: "Authentification"});
    }
    else {
      let formInfos = {...this.tokenInfos, newPassword: this.f.newPassword.value};
      switch (this.action) {
        case 'FIRST_CONNEXION':
          this.authService.authenticateUserWithFirstUrlConnexion(formInfos).subscribe(response => {
            if (response?.status === 'OK') {
                this.authService.saveUserCredentials(response.payload);
                this.router.navigateByUrl('/dashboard');
                this.alertService.showAlert({message: `${ this.credentialsService.fullName }`, titre: 'Bienvenue ', status: 'OK'});
            } else this.alertService.showAlert({status: 'EXCEPTION', message: response?.message, titre: 'Authentification'});
          });
          break;
        case 'RESET_PASSWORD':
          this.authService.authenticateUserWithForgetPasswordUrlConnexion(formInfos).subscribe(response => {
            if (response?.status === 'OK') {
                this.authService.saveUserCredentials(response.payload);
                this.router.navigateByUrl('/dashboard');
                this.alertService.showAlert({message: `${this.credentialsService.userInfos?.prenom} ${this.credentialsService.userInfos?.nom.toUpperCase()}`, titre: 'Bienvenue', status: 'OK'});
            } else this.alertService.showAlert({status: 'EXCEPTION', message: response?.message, titre: 'Authentification'});
          });
          break;
        default:
          this.alertService.showAlert({status: 'EXCEPTION', message: 'le user n\'est pas passé par le lien du mail', titre: 'Authentification'});
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 5000);
          break;
      }
    }
  }

  ngOnInit(): void {
    let token = this.activatedRoute.snapshot.queryParams['token'];
    if (token != null) {
      let decodeToken: any = jwt_decode(token);

      this.f.login.setValue(decodeToken?.infos.login);

      const action = decodeToken?.infos?.action;
      this.action = action;

      this.tokenInfos = {password: decodeToken?.infos?.password, login: decodeToken?.infos?.login};

      switch (action) {
        case 'FIRST_CONNEXION':

          break;
        case 'RESET_PASSWORD':
          const todaysDate = new Date().getTime();
          const expirationDate = new Date(decodeToken['exp'] * 1000).getTime();
          const timeInterval = expirationDate - todaysDate;
          if (timeInterval <= 0) {
            this.alertService.showAlert({message: 'Le jeton d\'accès a expiré !', titre: 'Authentification', status: 'error'});

            setTimeout(() => {
              this.router.navigateByUrl('/login');
            }, 3000);
          }
          break;
      }
    } else {
      this.alertService.showAlert({status: "ERROR", message: "Vous devez vous connecter en utilisant le lien qui vous a été envoyé par e-mail !", titre: 'Authentification'});
      setTimeout(() => {
        this.authService.logout();
        this.router.navigate(['/login']);
      }, 1000);

    }
  }

  checkConstraintsValidation() {
    Object.keys(this.f).forEach(field => {
      const control = this.loginForm.get(field);
      control.markAsTouched({onlySelf: true});
    });
  }
}
