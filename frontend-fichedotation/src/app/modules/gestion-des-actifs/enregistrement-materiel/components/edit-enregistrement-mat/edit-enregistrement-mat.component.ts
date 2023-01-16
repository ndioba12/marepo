import { Component, OnInit } from '@angular/core';
import { TYPEDEMATERIEL } from '../../../../../shared/models/constantes-model';

@Component({
  selector: 'app-edit-enregistrement-mat',
  templateUrl: './edit-enregistrement-mat.component.html',
  styleUrls: ['./edit-enregistrement-mat.component.css']
})
export class EditEnregistrementMatComponent implements OnInit {

  
  typeMateriel :any[] = [];

  constructor() { }

  ngOnInit(): void {
    this.typeMateriel = TYPEDEMATERIEL;
  }

}
