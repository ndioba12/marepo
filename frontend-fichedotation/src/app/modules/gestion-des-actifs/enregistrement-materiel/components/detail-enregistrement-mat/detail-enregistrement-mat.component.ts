import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DetailMaterielDTO } from '../../models/materiel.model';
import { MaterielsService } from '../../services/materiels.service';

@Component({
  selector: 'app-detail-enregistrement-mat',
  templateUrl: './detail-enregistrement-mat.component.html',
  styleUrls: ['./detail-enregistrement-mat.component.css']
})
export class DetailEnregistrementMatComponent implements OnInit {
  model: any;
  materielDetails?: DetailMaterielDTO;
  id: any;

  constructor(
    private _materielService: MaterielsService,
    private route: ActivatedRoute,
    private router: Router
) { }

  ngOnInit(): void {
    this.id =this.route.snapshot.params['id'];
    this.getMateriel();
  }

  getMateriel(): void {
    
    this._materielService
      .getMateriel(this.id)
      .subscribe({
        next: (response) => {
          this.materielDetails = response.payload;
        },
      });
  }

  annuler(): void {
    this.router.navigate(['/gestion-des-actifs/liste-des-materiels-achats'])
  }

}
