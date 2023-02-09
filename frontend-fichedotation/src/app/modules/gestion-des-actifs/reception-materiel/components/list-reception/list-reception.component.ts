import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ModalDismissReasons, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import Swal from 'sweetalert2';
import { ReceptionService } from '../../services/reception-service';

@Component({
  selector: 'app-list-reception',
  templateUrl: './list-reception.component.html',
  styleUrls: ['./list-reception.component.css']
})
export class ListReceptionComponent implements OnInit {

  listDeMesDotations!:any[];
  closeResult = '';

  // type de signalement
  signalement:any[] = [
    { value:"Reformé" },
    { value:"Cédé" },
    { value:"Panne" },
    { value:"Hors Service" }
  ]

  constructor(private router:Router,private receptionService: ReceptionService,private modalService: NgbModal) { }
  page = 1;
	pageSize = 6;
	collectionSize!:number;

  ngOnInit(): void {
    this.listDeMesDotations = this.receptionService.getAllAffectation();
    this.refreshMateriels();
  }

  refreshMateriels() {
    this.listDeMesDotations = this.receptionService.getAllAffectation();
    this.collectionSize = this.listDeMesDotations!.length
		this.listDeMesDotations = this.listDeMesDotations.map((materiel, i) => ({ id: i + 1, ...materiel })).slice(
			(this.page - 1) * this.pageSize,
			(this.page - 1) * this.pageSize + this.pageSize,
		);
	}

  details(item:any):void{
    this.router.navigate(['/gestion-des-actifs/liste-des-affectations/',item,'detail-materiel']);
  }
  Signalisation(content:any) {
    this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title',centered: true }).result.then(
			(result) => {
				this.closeResult = `Closed with: ${result}`;
			},
			(reason) => {
				this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
			},
		);
    // Swal.fire({
    //   title: 'Êtes-vous sûr de faire cet action ?',
    //   text: "Vous ne pourrez pas revenir en arrière !",
    //   icon: 'warning',
    //   showCancelButton: true,
    //   confirmButtonColor: '#1C5EAB',
    //   cancelButtonColor: '#FF4D4F',
    //   confirmButtonText: 'Confirmer',
    //   cancelButtonText: 'Annuler'
    // }).then((avis) => {
    //   if (avis.isConfirmed) {
    //     Swal.fire({
    //       title: 'Signalisation',
    //       text: "Le matériel a été signalé.",
    //       icon: 'success',
    //       timer: 2000,
    //       showCancelButton: false,
    //       showConfirmButton: false
    //     })
    //   }
    // })
  }

  Accepter() {
    Swal.fire({
      title: 'Êtes-vous sûr de faire cet action ?',
      text: "Vous ne pourrez pas revenir en arrière !",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#1C5EAB',
      cancelButtonColor: '#FF4D4F',
      confirmButtonText: 'Confirmer',
      cancelButtonText: 'Annuler'
    }).then((avis) => {
      if (avis.isConfirmed) {
        Swal.fire({
          title: 'Acceptation',
          text: "L'élément a été accepté.",
          icon: 'success',
          timer: 2000,
          showCancelButton: false,
          showConfirmButton: false
        })
      }
    })
  }

  Refuser() {
    Swal.fire({
      title: 'Êtes-vous sûr de faire cet action ?',
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
          title: 'Refusé',
          text: "L'élément a été refusé.",
          icon: 'success',
          timer: 2000,
          showCancelButton: false,
          showConfirmButton: false
        })
      }
    })
  }


	Affectation(content: any) {
		this.modalService.open(content, { ariaLabelledBy: 'modal-basic-title',centered: true }).result.then(
			(result) => {
				this.closeResult = `Closed with: ${result}`;
			},
			(reason) => {
				this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
			},
		);
	}

	private getDismissReason(reason: any): string {
		if (reason === ModalDismissReasons.ESC) {
			return 'by pressing ESC';
		} else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
			return 'by clicking on a backdrop';
		} else {
			return `with: ${reason}`;
		}
	}
}
