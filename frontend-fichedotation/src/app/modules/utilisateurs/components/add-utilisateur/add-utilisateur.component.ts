import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormGroupDirective, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/shared/helpers/others/alert.service';
import { StaticDataService } from 'src/app/shared/services/static-data.service';
import { UtilisateurDTO } from '../../models/user.model';
import { UtilisateursService } from '../../services/utilisateurs.service';

@Component({
  selector: 'app-add-utilisateur',
  templateUrl: './add-utilisateur.component.html',
  styleUrls: ['./add-utilisateur.component.css']
})
export class AddUtilisateurComponent implements OnInit {
  formDirective!: FormGroupDirective;

  formAdd: FormGroup;
  validEmailLogin = false;
  initialData: any;

  profils: any[] = [];
  entites: any[] = [];

  constructor(
    private alertService: AlertService,
    private utilisateursService: UtilisateursService,
    private staticDataService: StaticDataService,
    private fb: FormBuilder,
    private router: Router
  ) { }

  ngOnInit(): void {

    this.formAdd = this.fb.group({
      matricule: [''],
      prenom: [''],
      nom: [''],
      email: ['', [Validators.required]],
      linkedProfil: [null],
      linkedEntite: [null]
    });
    this.initialData = this.formAdd.value;

    this.staticDataService.allProfils().subscribe(res => {
      if (res.status === 'OK') {
        this.profils = res.payload;
      }
    });
    this.staticDataService.allEntites().subscribe(res => {
      if (res.status === 'OK') {
        this.entites = res.payload;
      }
    });
  }

  saveUtilisateur(): void {
    // if (this.formAdd.invalid) {
    //   this.checkConstraintsValidation();
    // } else {
      this.alertService.showSwal('CONFIRM', () => {
        const dto: UtilisateurDTO = {...this.formAdd.value};
        this.utilisateursService.saveUtilisateur(dto).subscribe(res => {
          this.alertService.showAlert({status: res?.status, message: res?.message, titre: 'Utilisateurs'});
          if (res.status === 'OK') {
            this.router.navigateByUrl('/gestion-des-utilisateurs');
          }
        });
      });
   // }
  }

  cancel(): void {
    this.formDirective.resetForm(this.initialData);
  }

  emailValidationLogin(e): void {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    this.validEmailLogin = re.test(String(e).toLowerCase());
  }

}
