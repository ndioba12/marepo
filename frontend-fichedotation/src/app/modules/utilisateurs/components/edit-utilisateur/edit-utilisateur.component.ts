import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-utilisateur',
  templateUrl: './edit-utilisateur.component.html',
  styleUrls: ['./edit-utilisateur.component.css']
})
export class EditUtilisateurComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  entites:any[] = [
    { value:"Achats" },
    { value:"Support Technique" },
    { value:"Service logistique" },
    { value:"Comptable" },
    { value:"Personnel" },
    { value:"Controleur de gestion" },
    { value:"CSO" },
    { value:"CTO" }
  ]
}
