import { Component, OnInit } from '@angular/core';
import { TYPEDEMAINTENANCE } from '../../../../../shared/models/constantes-model';

@Component({
  selector: 'app-add-referencement',
  templateUrl: './add-referencement.component.html',
  styleUrls: ['./add-referencement.component.css']
})
export class AddReferencementComponent implements OnInit {

  typeMaintenance:any[] = [];

  constructor() { }

  ngOnInit(): void {
    this.typeMaintenance = TYPEDEMAINTENANCE
  }

}
