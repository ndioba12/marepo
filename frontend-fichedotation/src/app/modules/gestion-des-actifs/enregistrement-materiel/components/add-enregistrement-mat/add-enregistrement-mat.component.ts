import { Component, OnInit } from '@angular/core';
import { TYPEDEMATERIEL } from '../../../../../shared/models/constantes-model';

@Component({
  selector: 'app-add-enregistrement-mat',
  templateUrl: './add-enregistrement-mat.component.html',
  styleUrls: ['./add-enregistrement-mat.component.css']
})
export class AddEnregistrementMatComponent implements OnInit {

  typeMateriel :any[] = [];

  constructor() { }

  ngOnInit(): void {
    this.typeMateriel = TYPEDEMATERIEL;
  }

}
