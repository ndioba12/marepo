import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CredentialsService } from 'src/app/modules/authentification/services/credentials.service';
import { Alert, AlertService } from 'src/app/shared/helpers/others/alert.service';
import { StaticDataService } from 'src/app/shared/services/static-data.service';
import Swal from 'sweetalert2';
import { AFFECTATIONS, TYPEDEMATERIEL, ETAT } from '../../../../../shared/models/constantes-model';
import { Affectation } from '../../../../../shared/models/materiel-model';
import { ListMaterielsAchatDTO } from '../../models/materiel.model';
import { MaterielsService } from '../../services/materiels.service';

@Component({
  selector: 'app-list-enregistrement-mat',
  templateUrl: './list-enregistrement-mat.component.html',
  styleUrls: ['./list-enregistrement-mat.component.css']
})
export class ListEnregistrementMatComponent implements OnInit {

  page = 1;
	pageSize = 10;
  //typeMateriel = TYPEDEMATERIEL;
  filter: string = '';
  collectionSize!: number;
  materiels!: ListMaterielsAchatDTO[];
  typeMateriels: any[] = [];
  etats: any[] = [];

  constructor(
    private router:Router,
    private _alertService: AlertService,
    private _materielService: MaterielsService,
    private staticDataService: StaticDataService
    ) {}

  ngOnInit(): void {
    this.staticDataService.allTypeMateriels().subscribe(res => {
      if (res.status === 'OK') {
        this.typeMateriels = res.payload;
      }
    });
    this.staticDataService.allEtats().subscribe(res => {
      if (res.status === 'OK') {
        this.etats = res.payload;
      }
    });
    this.getMateriels();
  }


  addMateriel(): void {
    console.log("erreur");
    this.router.navigate(['/gestion-des-actifs/liste-des-materiels-achats/nouvel-enregistrement-materiel'])
  }

  seletedEtat(e: any) {
    this.filter = e.target.value;
    this.getMateriels();
  }

  seletedTypeImmobilisation(e: any) {
    this.filter = e.target.value;
    this.getMateriels();
  }

  selectedDate(e: any) {
    this.filter = e.target.value;
    this.getMateriels();
  }

  getMateriels(): void {
    this._materielService
      .listImmobilisations(this.page - 1, this.pageSize, this.filter)
      .subscribe({
        next: (data) => {
          if (data?.status === 'OK') {
            //Pas besoin d'afficher l'utilisateur qui est connecté dans la liste des utilisateurs
           // this.materiels = data.payload.filter((materiel: AchatDTO) => materiel.email !== this._credentialsService.username);
           this.materiels = data.payload;
           this.collectionSize = data.metadata!.totalElements;
          } else {
            this._alertService.showAlert({
              status: data?.status,
              message: data?.message,
              titre: 'Materiels',
            });
          }
        },
        error: (error) => console.error(error),
      });
  }


  deleteItem(id:any): void {
    this._alertService.showSwal('CONFIRM', () => {
      this._materielService.deleteMateriel(id).subscribe(rsp => {
        if (rsp?.status === 'OK') {
          const alert: Alert = {status: rsp?.status, message: rsp?.message, titre: 'Materiels'};
          this._alertService.showAlert(alert);
         // this.listUtilisateur(this.pageOptions);
         this.router.navigateByUrl('/gestion-des-actifs/liste-des-materiels-achats');
        }
      });
    });
  }



}
