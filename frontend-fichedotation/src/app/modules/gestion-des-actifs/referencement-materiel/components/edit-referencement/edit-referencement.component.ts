import { Component, OnInit } from '@angular/core';
import { TYPEDEMAINTENANCE } from '../../../../../shared/models/constantes-model';

@Component({
  selector: 'app-edit-referencement',
  templateUrl: './edit-referencement.component.html',
  styleUrls: ['./edit-referencement.component.css']
})
export class EditReferencementComponent implements OnInit {

  typeMaintenance:any[] = [];

  constructor() { }

  ngOnInit(): void {
    this.typeMaintenance = TYPEDEMAINTENANCE
  }

}
