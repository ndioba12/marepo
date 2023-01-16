import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-utilisateur',
  templateUrl: './add-utilisateur.component.html',
  styleUrls: ['./add-utilisateur.component.css']
})
export class AddUtilisateurComponent implements OnInit {

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
