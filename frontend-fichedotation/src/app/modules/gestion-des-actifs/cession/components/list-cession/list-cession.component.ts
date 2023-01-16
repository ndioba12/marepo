import { Component, OnInit } from '@angular/core';
import { cessionServices } from 'src/app/modules/gestion-des-actifs/cession/services/cession.services';
import Swal from 'sweetalert2';
import { CessionModel } from '../../models/cession.model';

@Component({
  selector: 'app-list-cession',
  templateUrl: './list-cession.component.html',
  styleUrls: ['./list-cession.component.css']
})
export class ListCessionComponent implements OnInit {
  tableCessionliste! : any[] ;
  page = 1;
	pageSize = 5;
	collectionSize!:number;

  constructor(private cessionservice : cessionServices , ) {}
  ngOnInit(): void {
    this.refreshMateriels();
  }

  refreshMateriels() {
    this.tableCessionliste = this.cessionservice.getAllMateriel();
    this.collectionSize = this.tableCessionliste!.length
		this.tableCessionliste = this.tableCessionliste.map((materiel, i) => ({ id: i + 1, ...materiel })).slice(
			(this.page - 1) * this.pageSize,
			(this.page - 1) * this.pageSize + this.pageSize,
		);
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

}
