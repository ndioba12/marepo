import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TYPEDEMATERIEL, ETAT } from 'src/app/shared/models/constantes-model';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-list-utilisateur',
  templateUrl: './list-utilisateur.component.html',
  styleUrls: ['./list-utilisateur.component.css']
})
export class ListUtilisateurComponent implements OnInit {
[x: string]: any;

 
  page = 1;
	pageSize = 5;
	collectionSize = UTIILISATEURS.length;
	utilisateurs!: IUtilisateur[];
  typeMateriel = TYPEDEMATERIEL;
  etat = ETAT;

  text = "";

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.refreshUtilisateurs();
  }

  addMateriel(): void {
    this.router.navigate(['/gestion-des-utilisateurs/nouvel-utilisateur'])
  }

  refreshUtilisateurs() {
		this.utilisateurs = UTIILISATEURS.map((utilisateur, i) => ({ id: i + 1, ...utilisateur })).slice(
			(this.page - 1) * this.pageSize,
			(this.page - 1) * this.pageSize + this.pageSize,
		);
	}

  changeStatus(user: IUtilisateur): void {
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
        if(user.status == 'Actif'){
          this.text = "Débloqué";
          user.status = "Inactif"
        }else{
          this.text = "Bloqué";
          user.status = "Actif";
        }

        Swal.fire({
          title: this.text,
          text: `L'élément a été ${this.text}.`,
          icon: 'success',
          timer: 2000,
          showCancelButton: false,
          showConfirmButton: false
        })
      }
    })
  }

}

export interface IUtilisateur{
  matricule:string,
  prenom:string,
  nom:string,
  entite:string,
  profil:string,
  status:"Actif"|"Inactif"
}

export const UTIILISATEURS:IUtilisateur[] = [
  {
    matricule:"ref001",
    prenom:"Amy",
    nom:"SARR",
    entite:"ODS",
    profil:"Achats",
    status:"Actif"
  },
  {
    matricule:"ref002",
    prenom:"Mbaye Sokhna",
    nom:"THIAM",
    entite:"ODS",
    profil:"Support Technique",
    status:"Actif"
  },
  {
    matricule:"ref003",
    prenom:"Adama",
    nom:"GUEYE",
    entite:"Factory",
    profil:"Service logistique",
    status:"Inactif"
  },
  {
    matricule:"ref004",
    prenom:"Ayssatou",
    nom:"NDIAYE",
    entite:"ODS",
    profil:"Comptable",
    status:"Inactif"
  },
  {
    matricule:"ref005",
    prenom:"Libasse",
    nom:"YADE",
    entite:"Factory",
    profil:"Personnel",
    status:"Actif"
  },
  {
    matricule:"ref006",
    prenom:"Serigne Fallou",
    nom:"NDIAYE",
    entite:"Factory",
    profil:"Controleur de gestion",
    status:"Actif"
  },
  {
    matricule:"ref007",
    prenom:"Alioune Badara",
    nom:"NDIAYE",
    entite:"Factory",
    profil:"CSO",
    status:"Actif"
  },
]