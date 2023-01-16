import {Component, OnInit, ViewChild} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AlertService} from '../../../../@shared/helpers/others/alert.service';
import {UtilisateurDTO} from '../../models/user.model';
import {UtilisateursService} from '../../services/utilisateurs.service';
import {StaticDataService} from '../../../../@shared/services/static-data.service';
import {MyErrorStateMatcher} from '../ajout-utilisateur/ajout-utilisateur.component';

@Component({
  selector: 'app-edit-utilisateur',
  templateUrl: './edit-utilisateur.component.html',
  styleUrls: ['./edit-utilisateur.component.css']
})
export class EditUtilisateurComponent implements OnInit {
  @ViewChild(FormGroupDirective)
  formDirective!: FormGroupDirective;

  matcher = new MyErrorStateMatcher();

  form: FormGroup;
  initialData: any;


  utilisateur: UtilisateurDTO;
  profils: any[] = [];

  validEmailLogin = false;

  constructor(
    private alertService: AlertService,
    private utilisateursService: UtilisateursService,
    private staticDataService: StaticDataService,
    private fb: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute
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

    const id = this.activatedRoute.snapshot.paramMap.get('id') || null;
    // tslint:disable-next-line:radix
    this.utilisateursService.getUtilisateur(parseInt(id)).subscribe(rsp => {
      if (rsp?.status === 'OK') {
        this.utilisateur = rsp?.payload;
        this.form.patchValue({
          prenom: this.utilisateur?.prenom,
          nom: this.utilisateur?.nom,
          email: this.utilisateur?.email,
          telephone: this.utilisateur?.telephone,
          adresse: this.utilisateur?.adresse,
          linkedProfil: this.utilisateur?.linkedProfil?.id
        });
      } else {
        this.alertService.showAlert({status: rsp?.status, message: rsp?.message, titre: 'Utilisateurs'});
        this.cancel();
      }
    });
  }

  editUtilisateur(): void {
    if (this.form.invalid) {
      this.checkConstraintsValidation();
    } else {
      this.alertService.showSwal('CONFIRM', () => {
        const dto: UtilisateurDTO = {...this.form.value};
        this.utilisateursService.editUtilisateur(this.utilisateur?.id, dto).subscribe(rsp => {
          this.alertService.showAlert({status: rsp?.status, message: rsp?.message, titre: 'Utilisateurs'});
          if (rsp.status === 'OK') {
            this.router.navigateByUrl('/users/list');
          }
        });
      });
    }
  }

  cancel(): void {
    this.router.navigateByUrl('/users/list');
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
