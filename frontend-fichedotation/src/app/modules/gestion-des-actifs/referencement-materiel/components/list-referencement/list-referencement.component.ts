import { Component, OnInit } from '@angular/core';
import { CredentialsService } from 'src/app/modules/authentification/services/credentials.service';
import { AlertService } from 'src/app/shared/helpers/others/alert.service';
import { ResponseApi } from 'src/app/shared/models/response-api.model';
import { StaticDataService } from 'src/app/shared/services/static-data.service';
import Swal from 'sweetalert2';
import {
  AFFECTATIONS,
  TYPEDEMATERIEL,
  ETAT,
} from '../../../../../shared/models/constantes-model';
import { IReferenceImmoDTO } from '../../models/referenceImmo.models';
import { ReferencementMaterielService } from '../../services/referencement-materiel.service';

@Component({
  selector: 'app-list-referencement',
  templateUrl: './list-referencement.component.html',
  styleUrls: ['./list-referencement.component.css'],
})
export class ListReferencementComponent implements OnInit {
  page = 1;
  pageSize = 5;
  filterEtat = '';
  filterModele = '';
  filterDateAquisition = "";
  collectionSize: number;
  typeMateriel = TYPEDEMATERIEL;
  etats: any[];
  status: any[];
  materiels!: IReferenceImmoDTO[];
  userRole: any;

  constructor(
    private _alertService: AlertService,
    private _refMaterielService: ReferencementMaterielService,
    private _credentialService: CredentialsService,
    private _staticDataService: StaticDataService
  ) {
    this.userRole = this._credentialService.userAuthorities[0];
  }

  ngOnInit(): void {
    this._staticDataService.allEtats().subscribe({
      next: (response: ResponseApi) => {
        this.etats = response.payload;
      },
    });
    this._staticDataService.allStatuts().subscribe({
      next: (response: ResponseApi) => {
        this.status = response.payload;
      },
    });

    //le profil technique ne voit que les matériels de catégorie technique, et le profil logistique que les materiels de catégorie logistique
    switch (this.userRole.authority) {
      case 'SET':
        this.getMateriels('technique');
        break;
      case 'SEL':
        this.getMateriels('logistique');
        break;
      default:
        this.getMateriels();
        break;
    }
  }

  getMateriels(filter: string = ''): void {
    this._refMaterielService
      .listImmoReference(this.page - 1, this.pageSize, filter)
      .subscribe({
        next: (data) => {
          if (data?.status === 'OK') {
            this.materiels = data.payload;
            this.collectionSize = data.metadata!.totalElements;
          } else {
            this._alertService.showAlert({
              status: data?.status,
              message: data?.message,
              titre: 'Materiels',
            });
          }
        },
        error: (error) => console.error(error),
      });
  }
}
