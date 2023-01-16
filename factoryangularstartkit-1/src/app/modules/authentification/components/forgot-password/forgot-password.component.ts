import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthService} from '../../services/auth.service';
import {AlertService} from '../../../../@shared/helpers/others/alert.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  hide = true;
  loginForm: FormGroup;

  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private authService: AuthService,
    private alertService: AlertService
  ) {
  }

  get f() {
    return this.loginForm.controls;
  }

  // On SignIn link click
  onSignIn() {
    this.router.navigate(['login'], {relativeTo: this.route.parent});
  }

  // On Signup link click
  onSignup() {
    this.router.navigate(['register'], {relativeTo: this.route.parent});
  }

  onForgetPassword() {
    if (this.loginForm.invalid) this.checkConstraintsValidation();
    else {
      this.authService.handleForgetPassword(this.f.login.value).subscribe(response => {
        this.alertService.showAlert({message: response?.message, status: response?.status, titre: 'Authentification'});
        this.router.navigateByUrl("/auth/login");
      });
    }
  }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      login: ['', [Validators.required, Validators.email]]
    });
  }

  checkConstraintsValidation() {
    Object.keys(this.f).forEach(field => {
      const control = this.loginForm.get(field);
      control.markAsTouched({onlySelf: true});
    });
  }
}
