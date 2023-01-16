import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { cessionServices } from 'src/app/modules/gestion-des-actifs/cession/services/cession.services';
import { CessionModel } from '../../models/cession.model';

@Component({
  selector: 'app-add-cession',
  templateUrl: './add-cession.component.html',
  styleUrls: ['./add-cession.component.css']
})
export class AddCessionComponent implements OnInit {



  elements! : CessionModel[] | any ;
  constructor(private cessionservice : cessionServices , private route: ActivatedRoute) {}
  ngOnInit(): void {
  const id = this.route.snapshot.params['id']
  this.elements = this.cessionservice.getMaterielById(+id)

  }



}
