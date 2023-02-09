import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {CredentialsService} from '../../services/credentials.service';
import {CustomPasswordValidators} from '../../../../shared/helpers/validators/password.validators';
import {AlertService} from '../../../../shared/helpers/others/alert.service';
import Swal from "sweetalert2"

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class LoginComponent implements OnInit {
  hide: boolean = true;

  loginForm: FormGroup;
  formBuilder: FormBuilder = new FormBuilder();
  isValidIdent: boolean = true;
  isValidPwd: boolean = true;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private _authService: AuthService,
    private _credentialsService: CredentialsService,
    private _alertService: AlertService
  ) {
    //Initialisation du formulaire avec les validateurs de login et de mot de passe
    this.loginForm = this.formBuilder.group({
      login: [
        '',
        [
          Validators.required,
          Validators.email,
          Validators.minLength(5),
          Validators.maxLength(30)
        ],
      ],
      remember: [],
      password: [
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
              hasSpecialCharacters: true
            }
          )]
        )]
      });
    }

    //Récupère les input et permet de faire des controls dessus
    get f() {
      return this.loginForm.controls;
    }

    ngOnInit() {
      document.body.className = "bg-login";
      localStorage.setItem('premiere-connexion', "false");

      if (this._credentialsService.isExpiredToken()) {
        this._authService.logout();
      }
      else {
        this.router.navigateByUrl('/auth/login');
      }
    }
  // On Forgotpassword link click
    onForgotpassword() {
      this.router.navigate(['forgot-password'], {relativeTo: this.route.parent});
    }

    ngOnDestroy() {
      document.body.className = "";
    }


  onLogin(): void {

    this._authService.signin(this.loginForm.value).subscribe((response) => {
      if (response?.status === 'OK') {
        this._authService.saveUserCredentials(
          response.payload,
          this.f['remember'].value
        );
        const loggedUser = this._credentialsService.userInfos;

        if (!loggedUser?.firstLog) {
          this.router.navigate(
            [
              this.route.snapshot.queryParams['redirect'] ||
                '/dashboard',
            ],
            { replaceUrl: true }
          );

          let alert = {
            message: `Bienvenue ${this._credentialsService.userInfos?.prenomNom}`,
            titre: 'Tableau de bord',
            status: 'OK',
          };
          this._alertService.showAlert(alert);
        } else {
          this._alertService.showAlert({
            message: "Veillez verifier votre boite email pour réinitialiser votre mot de passe.",
            titre: "Authentification"
          })
        }
      } else {
        this._alertService.showAlert({
          status: 'error',
          message: response.message!,
          titre: 'Authentification',
        });
      }
    });
  }
}
