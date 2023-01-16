import {Component, OnInit, ViewChild} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {UtilisateursService} from '../../services/utilisateurs.service';
import {AlertService} from '../../../../@shared/helpers/others/alert.service';
import {Router} from '@angular/router';
import {ErrorStateMatcher} from '@angular/material/core';
import {StaticDataService} from '../../../../@shared/services/static-data.service';
import {UtilisateurDTO} from '../../models/user.model';

@Component({
  selector: 'app-ajout-utilisateur',
  templateUrl: './ajout-utilisateur.component.html',
  styleUrls: ['./ajout-utilisateur.component.css']
})
export class AjoutUtilisateurComponent implements OnInit {
  @ViewChild(FormGroupDirective)
  formDirective!: FormGroupDirective;

  matcher = new MyErrorStateMatcher();

  form: FormGroup;
  initialData: any;

  defaultProfil: any = null;
  profils: any[] = [];

  validEmailLogin = false;

  constructor(
    private alertService: AlertService,
    private utilisateursService: UtilisateursService,
    private staticDataService: StaticDataService,
    private fb: FormBuilder,
    private router: Router
  ) {
  }

  get f(): { [p: string]: AbstractControl } {
    return this.form.controls;
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      prenom: ['', [Validators.minLength(2), Validators.maxLength(254), Validators.required]],
      nom: ['', [Validators.minLength(2), Validators.maxLength(254), Validators.required]],
      email: ['', [Validators.email, Validators.required]],
      telephone: ['', Validators.required],
      adresse: ['', [Validators.minLength(2), Validators.maxLength(254)]],
      linkedProfil: [null, Validators.required]
    });
    this.initialData = this.form.value;

    this.staticDataService.allProfils().subscribe(rsp => {
      if (rsp.status === 'OK') {
        this.profils = rsp.payload;
      }
    });
  }

  saveUtilisateur(): void {
    if (this.form.invalid) {
      this.checkConstraintsValidation();
    } else {
      this.alertService.showSwal('CONFIRM', () => {
        const dto: UtilisateurDTO = {...this.form.value};
        this.utilisateursService.saveUtilisateur(dto).subscribe(rsp => {
          this.alertService.showAlert({status: rsp?.status, message: rsp?.message, titre: 'Utilisateurs'});
          if (rsp.status === 'OK') {
            this.router.navigateByUrl('/users/list');
          }
        });
      });
    }
  }

  cancel(): void {
    this.formDirective.resetForm(this.initialData);
  }

  checkConstraintsValidation(): void {
    Object.keys(this.f).forEach(field => {
      const control = this.form.get(field);
      control.markAsTouched({onlySelf: true});
    });
  }

  emailValidationLogin(e): void {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    this.validEmailLogin = re.test(String(e).toLowerCase());
  }
}

export class MyErrorStateMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}
