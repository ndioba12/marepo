import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Affectation } from 'src/app/shared/models/materiel-model';
import { ReceptionService } from '../../services/reception-service';

@Component({
  selector: 'app-detail-reception',
  templateUrl: './detail-reception.component.html',
  styleUrls: ['./detail-reception.component.css']
})
export class DetailReceptionComponent implements OnInit {

  receptionMateriel:Affectation|any;

  constructor(private receptionService: ReceptionService,private route:ActivatedRoute) { }

  ngOnInit(): void {
      const id = this.route.snapshot.params['id']
      this.receptionMateriel = this.receptionService.getAffectationById(+id)
  }
}
