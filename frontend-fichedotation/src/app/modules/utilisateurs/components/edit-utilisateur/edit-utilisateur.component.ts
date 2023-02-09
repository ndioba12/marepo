import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormGroupDirective } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from 'src/app/shared/helpers/others/alert.service';
import { ResponseApi } from 'src/app/shared/models/response-api.model';
import { StaticDataService } from 'src/app/shared/services/static-data.service';
import { UtilisateurDTO } from '../../models/user.model';
import { UtilisateursService } from '../../services/utilisateurs.service';

@Component({
  selector: 'app-edit-utilisateur',
  templateUrl: './edit-utilisateur.component.html',
  styleUrls: ['./edit-utilisateur.component.css'],
})
export class EditUtilisateurComponent implements OnInit {
  formDirective!: FormGroupDirective;
  formEdit: FormGroup;
  validEmailLogin = false;
  initialData: any;

  profils: any[] = [];
  entites: any[] = [];
  userDetails?: UtilisateurDTO;
  userId: number;

  constructor(
    private _userService: UtilisateursService,
    private activatedRoute: ActivatedRoute,
    private alertService: AlertService,
    private staticDataService: StaticDataService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  get f() {
    return this.formEdit.controls;
  }

  ngOnInit(): void {
    this.formEdit = this.fb.group({
      matricule: [''],
      prenom: [''],
      nom: [''],
      email: [''],
      linkedProfil: [null],
      linkedEntite: [null]
    });

    this.initialData = this.formEdit.value;

    this.activatedRoute.params.subscribe({
      next: (params) => {
        this.userId = params['id'];
        this._userService.getUtilisateur(this.userId).subscribe({
          next: (response: ResponseApi) => {
            if (response.status === 'OK')
              this.userDetails = response.payload;
          },
          complete: () => {
            this.formEdit.patchValue({
              ...this.userDetails,
              linkedProfil: this.userDetails?.linkedProfil?.id,
              linkedEntite: this.userDetails?.linkedEntite?.id
            });
          }
        });
      },
    });

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

  editUtilisateur(): void {

      this.alertService.showSwal('CONFIRM', () => {
        const dto: UtilisateurDTO = {...this.formEdit.value};
        this._userService.editUtilisateur(this.userId, dto).subscribe(res => {
          this.alertService.showAlert({status: res?.status, message: res?.message, titre: 'Utilisateurs'});
          if (res.status === 'OK') {
            this.router.navigateByUrl('/gestion-des-utilisateurs');
          }
        });
      });

  }

  cancel(): void {
    this.router.navigateByUrl("gestion-des-utilisateurs")
  }
}
