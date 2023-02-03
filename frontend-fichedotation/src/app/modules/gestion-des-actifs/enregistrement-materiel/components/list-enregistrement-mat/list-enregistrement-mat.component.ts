import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/shared/helpers/others/alert.service';
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
  typeMateriel = TYPEDEMATERIEL;
  filter: string = '';
  collectionSize!: number;
  materiels!: ListMaterielsAchatDTO[];


  constructor(
    private router:Router,
    private _alertService: AlertService,
    private _materielService: MaterielsService,) {}

  ngOnInit(): void {
    this.getMateriels();
   // this.refreshAffectations();
  }


  addMateriel(): void {
    console.log("erreur");
    this.router.navigate(['/gestion-des-actifs/liste-des-materiels-achats/nouvel-enregistrement-materiel'])
  }

  deleteItem() {
    Swal.fire({
      title: 'Êtes-vous sûr de faire cette suppression ?',
      text: "Vous ne pourrez pas revenir en arrière !",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#1C5EAB',
      cancelButtonColor: '#FF4D4F',
      confirmButtonText: 'Confirmer',
      cancelButtonText: 'Annuler'
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire({
          title: 'Supprimé',
          text: "L'élément a été supprimé.",
          icon: 'success',
          timer: 2000,
          showCancelButton: false,
          showConfirmButton: false
        })
      }
    })
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

  annuler(): void {
    this.router.navigate(['/gestion-des-actifs/liste-des-materiels-achats'])
  }

}
