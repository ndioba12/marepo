import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AFFECTATIONS, TYPEDEMATERIEL, ETAT } from '../../../../../shared/models/constantes-model';
import { Affectation } from '../../../../../shared/models/materiel-model';

@Component({
  selector: 'app-list-enregistrement-mat',
  templateUrl: './list-enregistrement-mat.component.html',
  styleUrls: ['./list-enregistrement-mat.component.css']
})
export class ListEnregistrementMatComponent implements OnInit {

  page = 1;
	pageSize = 5;
	collectionSize = AFFECTATIONS.length;
	affectations!: Affectation[];
  typeMateriel = TYPEDEMATERIEL;
  etat = ETAT;

  constructor(private router:Router) {}

  ngOnInit(): void {
    this.refreshAffectations();
  }


  addMateriel(): void {
    this.router.navigate(['/gestion-des-actifs/liste-des-materiels/nouvel-enregistrement-materiel'])
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


  refreshAffectations() {
		this.affectations = AFFECTATIONS.map((affectation, i) => ({ id: i + 1, ...affectation })).slice(
			(this.page - 1) * this.pageSize,
			(this.page - 1) * this.pageSize + this.pageSize,
		);
	}

}
