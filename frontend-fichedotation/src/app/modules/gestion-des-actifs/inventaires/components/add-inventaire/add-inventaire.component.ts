import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { InventaireModel } from '../../models/inventaire.model';
import { InventaireServices } from '../../services/iventaire.services';

@Component({
  selector: 'app-add-inventaire',
  templateUrl: './add-inventaire.component.html',
  styleUrls: ['./add-inventaire.component.css']
})
export class AddInventaireComponent implements OnInit {

  elements! : InventaireModel[] | any ;
  constructor(private inventaireservices : InventaireServices , private route: ActivatedRoute) {}
  ngOnInit(): void {
  const id = this.route.snapshot.params['id']
  this.elements = this.inventaireservices.getMaterielById(+id)

  }

}
