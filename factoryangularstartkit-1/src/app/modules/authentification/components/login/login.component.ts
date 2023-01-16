import {Component, ElementRef, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {CredentialsService} from '../../services/credentials.service';
import {CustomPasswordValidators} from '../../../../@shared/helpers/validators/password.validators';
import {AlertService} from '../../../../@shared/helpers/others/alert.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private toggleButton: any;
  private sidebarVisible: boolean;
  private nativeElement: Node;

  hide = true;
  loginForm: FormGroup;

  constructor(
    private element: ElementRef,
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private authService: AuthService,
    private credentialsService: CredentialsService,
    private activatedRoute: ActivatedRoute,
    private alertService: AlertService
  ) {
    this.nativeElement = element.nativeElement;
    this.sidebarVisible = false;

    this.loginForm = this.formBuilder.group({
      login: ['', [Validators.required, Validators.email, Validators.minLength(5), Validators.maxLength(30)]],
      remember: [],
      password: ['', Validators.compose([
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
      )]
    });
  }

  get f() {
    return this.loginForm.controls;
  }

  ngOnInit() {
    let navbar: HTMLElement = this.element.nativeElement;
    this.toggleButton = navbar.getElementsByClassName('navbar-toggle')[0];
    const body = document.getElementsByTagName('body')[0];
    body.classList.add('login-page');
    body.classList.add('off-canvas-sidebar');
    const card = document.getElementsByClassName('card')[0];
    card.classList.remove('card-hidden');

    if (this.credentialsService.isExpiredToken()) {
      this.authService.logout();
    } else {
      this.router.navigateByUrl('/dashboard');
    }

  }


  // On Forgotpassword link click
  onForgotpassword() {
    this.router.navigate(['forgot-password'], {relativeTo: this.route.parent});
  }

  onLoginSubmit() {
    if (this.loginForm.invalid) {
      this.checkConstraintsValidation();
    } else {
      this.authService.signin(this.loginForm.value).subscribe(response => {

        if (response?.status === 'OK') {
          console.log(response?.payload);
          this.authService.saveUserCredentials(response.payload, this.f['remember'].value);
          const loggedUser = this.credentialsService.userInfos;

          if (!loggedUser?.firstLog) {
            this.router.navigate([this.route.snapshot.queryParams['redirect'] || '/dashboard'], {replaceUrl: true});

            let alert = {
              message: `Bienvenue ${this.credentialsService.userInfos?.prenomNom}`,
              titre: 'Tableau de bord',
              status: 'OK'
            };
            this.alertService.showAlert(alert);
          } else {
            this.router.navigateByUrl('/auth/reset-password');
          }
        } else {
          this.alertService.showAlert({status: 'error', message: response?.message, titre: 'Authentification'});
        }
      });
    }
  }

  checkConstraintsValidation() {
    Object.keys(this.f).forEach(field => {
      const control = this.loginForm.get(field);
      control.markAsTouched({onlySelf: true});
    });
  }

  ngOnDestroy() {
    const body = document.getElementsByTagName('body')[0];
    body.classList.remove('login-page');
    body.classList.remove('off-canvas-sidebar');
  }
}
