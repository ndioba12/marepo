import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../services/auth.service';
import {CredentialsService} from '../../services/credentials.service';
import jwt_decode from 'jwt-decode';
import Swal from 'sweetalert2';
import { AlertService } from 'src/app/shared/helpers/others/alert.service';
import { CustomPasswordValidators } from 'src/app/shared/helpers/validators/password.validators';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css'],
})

export class ResetPasswordComponent implements OnInit {
  hide: boolean = true;
  hide2: boolean = true;
  tokenInfos: any;
  action: string = '';
  resetPasswordForm: FormGroup;
  formBuilder: FormBuilder = new FormBuilder();


  constructor(
    private _authService: AuthService,
    private _alertService: AlertService,
    private _credentialsService: CredentialsService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.resetPasswordForm = this.formBuilder.group(
      {
        login: ['', [Validators.required, Validators.email]],
        newPassword: [
          '',
          Validators.compose([
            Validators.required,
            Validators.minLength(8),
            CustomPasswordValidators.patternValidator(/\d/, {
              hasNumber: true,
            }),
            // check whether the entered password has upper case letter
            CustomPasswordValidators.patternValidator(/[A-Z]/, {
              hasCapitalCase: true,
            }),
            // check whether the entered password has a lower case letter
            CustomPasswordValidators.patternValidator(/[a-z]/, {
              hasSmallCase: true,
            }),
            // check whether the entered password has a special character
            CustomPasswordValidators.patternValidator(
              /[ !@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/,
              {
                hasSpecialCharacters: true,
              }
            ),
          ]),
        ],
        newPasswordConfirm: ['', Validators.required],
      },
      { validator: CustomPasswordValidators.PasswordValidator }
    );
  }

  get f() {
    return this.resetPasswordForm.controls;
  }

  ngOnInit() {
    document.body.className = 'bg-login';
    localStorage.setItem('premiere-connexion', 'false');

    let token = this.activatedRoute.snapshot.queryParams['token'];
    if (token != null) {
      let decodeToken: any = jwt_decode(token);

      this.f['login'].setValue(decodeToken?.infos.login);

      const action = decodeToken?.infos?.action;
      this.action = action;

      this.tokenInfos = {
        password: decodeToken?.infos?.password,
        login: decodeToken?.infos?.login,
      };

      switch (action) {
        case 'FIRST_CONNEXION':
          break;
        case 'RESET_PASSWORD':
          const todaysDate = new Date().getTime();
          const expirationDate = new Date(decodeToken['exp'] * 1000).getTime();
          const timeInterval = expirationDate - todaysDate;
          if (timeInterval <= 0) {
            this._alertService.showAlert({
              message: "Le jeton d\'accès a expiré !",
              titre: 'Authentification',
              status: 'error',
            });

            setTimeout(() => {
              this.router.navigateByUrl('/auth/login');
            }, 3000);
          }
          break;
      }
    } else {
      this._alertService.showAlert({
        status: 'ERROR',
        message:
          'Vous devez vous connecter en utilisant le lien qui vous a été envoyé par e-mail !',
        titre: 'Authentification',
      });
      setTimeout(() => {
        this._authService.logout();
        this.router.navigate(['/login']);
      }, 1000);
    }
  }

  ngOnDestroy() {
    document.body.className = '';
  }

  resetPassword() {
    if (this.resetPasswordForm.invalid) {
      this.checkConstraintsValidation();
      if (
        this.resetPasswordForm?.errors?.['misMatch'] &&
        !this.f['newPasswordConfirm']?.errors &&
        !this.f['newPassword']?.errors
      )
        this._alertService.showAlert({
          status: 'ERROR',
          message: 'Les deux mot de passe ne correspondent pas !',
          titre: 'Authentification',
        });
    } else {
      let formInfos = {
        ...this.tokenInfos,
        newPassword: this.f['newPassword'].value,
      };
      switch (this.action) {
        case 'FIRST_CONNEXION':
          this._authService
            .authenticateUserWithFirstUrlConnexion(formInfos)
            .subscribe((response) => {
              if (response?.status === 'OK') {
                this._authService.saveUserCredentials(response.payload);
                this.router.navigateByUrl('/dashboard');
                this._alertService.showAlert({
                  message: `${this._credentialsService.fullName}`,
                  titre: 'Bienvenue ',
                  status: 'OK',
                });
              } else
                this._alertService.showAlert({
                  status: 'EXCEPTION',
                  message: response?.message!,
                  titre: 'Authentification',
                });
            });
          break;
        case 'RESET_PASSWORD':
          this._authService
            .authenticateUserWithForgetPasswordUrlConnexion(formInfos)
            .subscribe((response) => {
              if (response?.status === 'OK') {
                this._authService.saveUserCredentials(response.payload);
                this.router.navigateByUrl('/dashboard');
                this._alertService.showAlert({
                  message: `${
                    this._credentialsService.userInfos?.prenom
                  } ${this._credentialsService.userInfos?.nom.toUpperCase()}`,
                  titre: 'Bienvenue',
                  status: 'OK',
                });
              } else
                this._alertService.showAlert({
                  status: 'EXCEPTION',
                  message: response?.message!,
                  titre: 'Authentification',
                });
            });
          break;
        default:
          this._alertService.showAlert({
            status: 'EXCEPTION',
            message: "le user n'est pas passé par le lien du mail",
            titre: 'Authentification',
          });
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 5000);
          break;
      }
    }
  }
  checkConstraintsValidation() {
    Object.keys(this.f).forEach((field) => {
      const control = this.resetPasswordForm.get(field);
      control!.markAsTouched({ onlySelf: true });
    });
  }

}
