import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormGroupDirective } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlertService } from 'src/app/shared/helpers/others/alert.service';
import { ResponseApi } from 'src/app/shared/models/response-api.model';
import { StaticDataService } from 'src/app/shared/services/static-data.service';
import { TYPEDEMATERIEL } from '../../../../../shared/models/constantes-model';
import { DetailMaterielDTO } from '../../models/materiel.model';
import { MaterielsService } from '../../services/materiels.service';

@Component({
  selector: 'app-edit-enregistrement-mat',
  templateUrl: './edit-enregistrement-mat.component.html',
  styleUrls: ['./edit-enregistrement-mat.component.css']
})
export class EditEnregistrementMatComponent implements OnInit {
  formDirective!: FormGroupDirective;
  form: FormGroup;
  validEmailLogin = false;
  initialData: any;

  typeMateriels: any[] = [];
  entites: any[] = [];
  materielDetails?: DetailMaterielDTO;
  id: number

  constructor(
    private alertService: AlertService,
    private _materielService: MaterielsService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private router: Router,
    private staticDataService: StaticDataService,
  ) { }

  ngOnInit(): void {
   
    this.form = this.fb.group({
      designation: [''],
      marque: [''],
      modele: [''],
      fournisseur: [''],
      accessoires: [''],
      dateAcquisition: [''],
      prixAcquisition: [''],
      typeImmobilisation: [null]
    });

    this.initialData = this.form.value;

    this.activatedRoute.params.subscribe({
      next: (params) => {
        this.id = params['id'];
        this._materielService.getMateriel(this.id).subscribe({
          next: (response: ResponseApi) => {
            if (response.status === 'OK')
              this.materielDetails = response.payload;
          },
          complete: () => {
            this.form.patchValue({
              ...this.materielDetails,
              typeImmobilisation: this.materielDetails?.typeImmobilisation?.id,
            });
          }
        });
      },
    });

    this.staticDataService.allTypeMateriels().subscribe(result => {
      if (result.status === 'OK') {
        this.typeMateriels = result.payload;
      }
    });
  }

  updateMateriel(): void {

    this.alertService.showSwal('CONFIRM', () => {
      const dto: DetailMaterielDTO = {...this.form.value};
      this._materielService.editMateriel(this.id, dto).subscribe(res => {
        this.alertService.showAlert({status: res?.status, message: res?.message, titre: 'Utilisateurs'});
        if (res.status === 'OK') {
          this.router.navigateByUrl('/gestion-des-actifs/liste-des-materiels-achats');
        }
      });
    });

}

annuler(): void {
  this.router.navigateByUrl('/gestion-des-actifs/liste-des-materiels-achats');
}
}
