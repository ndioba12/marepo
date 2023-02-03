import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormGroupDirective } from '@angular/forms';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/shared/helpers/others/alert.service';
import { StaticDataService } from 'src/app/shared/services/static-data.service';
import { AjoutMaterielAchatDTO } from '../../models/materiel.model';
import { MaterielsService } from '../../services/materiels.service';

@Component({
  selector: 'app-add-enregistrement-mat',
  templateUrl: './add-enregistrement-mat.component.html',
  styleUrls: ['./add-enregistrement-mat.component.css']
})
export class AddEnregistrementMatComponent implements OnInit {

  //typeMateriel :any[] = [];
  formAdd: FormGroup;
  formDirective!: FormGroupDirective;
  initialData: any;

  typeMateriels: any[] = [];
  categoriesMateriels: any[] = [];

  constructor( 
    private alertService: AlertService,
    private materielssService: MaterielsService,
    private staticDataService: StaticDataService,
    private fb: FormBuilder,
    private router: Router) { }

  ngOnInit(): void {
    this.staticDataService.allTypeMateriels().subscribe(res => {
      if (res.status === 'OK') {
        this.typeMateriels = res.payload;
      }
    });
    this.staticDataService.allCatMateriels().subscribe(res => {
      if (res.status === 'OK') {
        this.categoriesMateriels = res.payload;
      }
    });
    
    this.formAdd = this.fb.group({
      designation: [''],
      description: [''],
      marque: [''],
      modele: [''],
      accessoires: [''],
      fournisseur: [''],
      prixachat: [''],
      dateachat:[''],
      linkedTypeMateriel: [null],
      linkedCatMateriel: [null],
      dureeGarantie: [''],

    });
    this.initialData = this.formAdd.value;
   
  }


  addMateriel(): void {
      this.alertService.showSwal('CONFIRM', () => {
        const dto: AjoutMaterielAchatDTO = {...this.formAdd.value};
        this.materielssService.saveMateriel(dto).subscribe(res => {
          this.alertService.showAlert({status: res?.status, message: res?.message, titre: 'Materiels'});
          if (res.status === 'OK') {
            this.router.navigateByUrl('/gestion-des-actifs');
          }
        });
      });
   
  }

  annuler(): void {
    this.router.navigate(['/gestion-des-actifs/liste-des-materiels-achats'])
  }
}
