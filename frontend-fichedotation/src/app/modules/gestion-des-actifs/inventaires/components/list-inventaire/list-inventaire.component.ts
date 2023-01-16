import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { InventaireServices } from '../../services/iventaire.services';

@Component({
  selector: 'app-list-inventaire',
  templateUrl: './list-inventaire.component.html',
  styleUrls: ['./list-inventaire.component.css']
})
export class ListInventaireComponent implements OnInit {
  constructor(private inventaireservice : InventaireServices , ) {}
  tableInventaireliste! : any[] ;
  page = 1;
	pageSize = 5;
	collectionSize!:number;


  ngOnInit(): void {

this.refreshMateriels()
  }

  refreshMateriels() {
    this.tableInventaireliste = this.inventaireservice.getAllMateriel();
    this.collectionSize = this.tableInventaireliste!.length
		this.tableInventaireliste = this.tableInventaireliste.map((materiel, i) => ({ id: i + 1, ...materiel })).slice(
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
