import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/modules/authentification/services/auth.service';
import { CredentialsService } from 'src/app/modules/authentification/services/credentials.service';
import { AlertService } from 'src/app/shared/helpers/others/alert.service';
import { Logger } from 'src/app/shared/helpers/others/logger.service';
import { CustomPasswordValidators } from 'src/app/shared/helpers/validators/password.validators';
import { EResponseStatut } from 'src/app/shared/models/response-api.model';
import { IUpdatePasswordDTO } from '../../models/updatePassword.model';
import { UtilisateursService } from '../../services/utilisateurs.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-mon-compte',
  templateUrl: './mon-compte.component.html',
  styleUrls: ['./mon-compte.component.css'],
})
export class MonCompteComponent implements OnInit {
  hide: boolean = true;
  hide2: boolean = true;
  fullName: string = '';
  matricule: string = '';
  fonction: string = '';
  entite: string = '';
  updatePasswordForm: FormGroup;

  constructor(
    private _credentialService: CredentialsService,
    private _userService: UtilisateursService,
    private _alertService: AlertService,
    private _authService: AuthService,
    private formBuilder: FormBuilder,
  ) {
    this.fullName = this._credentialService.fullName;
    this.matricule = this._credentialService.userMatricule;
    this.fonction = this._credentialService.userFonction;
    this.entite = this._credentialService.userEntity;
  }

  ngOnInit(): void {
    this.updatePasswordForm = this.formBuilder.group(
      {
        currentPassword: ['', Validators.required],
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
    return this.updatePasswordForm.controls;
  }

  checkConstraintsValidation(): void {
    Object.keys(this.f).forEach((field) => {
      const control = this.updatePasswordForm.get(field);
      control!.markAsTouched({ onlySelf: true });
    });
  }

  updatePassword(): void {
    if (this.updatePasswordForm.invalid) {
      this.checkConstraintsValidation();
      if (
        this.updatePasswordForm?.errors?.['misMatch'] &&
        !this.f['confPassword']?.errors &&
        !this.f['newPassword']?.errors
      )
        this._alertService.showAlert({
          status: 'ERROR',
          message: 'Les deux mot de passe ne correspondent pas !',
          titre: 'Modification mot de passe',
        });
    } else {
      let updatePasswordDto: IUpdatePasswordDTO = {
        prevPassword: this.f['currentPassword'].value,
        newPassword: this.f['newPassword'].value,
        confPassword: this.f['newPasswordConfirm'].value,
      };
      this._userService.updatePasswordLoggedUser(updatePasswordDto).subscribe({
        next: (response) => {
          if (response.status === EResponseStatut.BAD_REQUEST){
            this._alertService.showAlert({
              status: 'error',
              message: response.message,
              titre: 'Modification mot de passe'
            });
          }
          else{
            Swal.fire({
              title: 'Modification mot de passe',
              text: "Votre mot de passe a été réinitialisé avec succès. Veillez vous reconnecter.",
              icon: 'info',
              showCancelButton: false,
              confirmButtonColor: '#3085d6',
              confirmButtonText: 'Fermer',
            })
            this._authService.logout();
          }
        },
        error: (error) => {
          console.error(error);
        },
      });
    }
  }
}
