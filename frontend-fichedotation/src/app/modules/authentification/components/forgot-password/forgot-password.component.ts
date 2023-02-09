import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from 'src/app/shared/helpers/others/alert.service';
import { ResponseApi } from 'src/app/shared/models/response-api.model';
import Swal from 'sweetalert2';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css'],
})
export class ForgotPasswordComponent implements OnInit {

  forgotPwdForm!: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private route: ActivatedRoute,
    private _authService: AuthService,
    private _alertService: AlertService
  ) {}

  ngOnInit() {
    document.body.className = 'bg-login';
    this.forgotPwdForm = this.formBuilder.group({
      login: ['', [Validators.required, Validators.email]]
    });
  }

  ngOnDestroy() {
    document.body.className = '';
  }

  ResetPassword(response: ResponseApi){
    Swal.fire({
      title: 'Réinitialisation',
      text: response.message!,
      icon: 'info',
      showCancelButton: false,
      confirmButtonColor: '#3085d6',
      confirmButtonText: 'Fermer',
    })
    this.router.navigate(['login'], { relativeTo: this.route.parent });
  }
  get f() {
    return this.forgotPwdForm.controls;
  }

  checkConstraintsValidation() {
    Object.keys(this.f).forEach((field) => {
      const control = this.forgotPwdForm.get(field);
      control!.markAsTouched({ onlySelf: true });
    });
  }

  onForgetPassword() {

    if (this.forgotPwdForm.invalid) {
      this.checkConstraintsValidation();
    } else {
      this._authService
          .handleForgetPassword(this.f['login'].value)
          .subscribe((response: ResponseApi) => {
            if (response.status === 'OK') {
              this.ResetPassword(response);
            } else {
              Swal.fire({
                title: 'Authenfication',
                text: response.message!,
                icon: 'error',
                showCancelButton: false,
                confirmButtonColor: 'red',
                confirmButtonText: 'Fermer',
              })
              // this._alertService.showAlert({
              //   status: 'error',
              //   message: response.message!,
              //   titre: 'Authentification',
              // });
            }
            // this.ResetPassword();
          });
    }
  }

}
