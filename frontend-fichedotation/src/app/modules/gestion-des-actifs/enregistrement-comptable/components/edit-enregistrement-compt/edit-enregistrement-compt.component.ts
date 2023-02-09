import { Component, OnInit } from '@angular/core';
import { TYPEDEMATERIEL } from '../../../../../shared/models/constantes-model';

@Component({
  selector: 'app-edit-enregistrement-compt',
  templateUrl: './edit-enregistrement-compt.component.html',
  styleUrls: ['./edit-enregistrement-compt.component.css']
})
export class EditEnregistrementComptComponent implements OnInit {

  typeMateriel :any[] = [];

  constructor() { }

  ngOnInit(): void {
    this.typeMateriel = TYPEDEMATERIEL;
  }

  dynamicArray:any[] = [
    { id:1, valeur:"200 000 Fcfa", date:"2022-12-30" , isEdit:false},
    { id:2, valeur:"150 000 Fcfa", date:"2022-12-31" , isEdit:false},
  ];

  newDynamic: any;

  addNewAmortissement() {
    this.dynamicArray.push({ valeur: '', date: '',isEdit:true });
    console.log('New row added successfully', 'New Row');
  }

  deleteRow(index:number) {
    this.dynamicArray.splice(index, 1);
  }
 
  onEdit(item: any) {
    this.dynamicArray.forEach(element => {
      element.isEdit = false;
    });
    item.isEdit = true;
  }

}
