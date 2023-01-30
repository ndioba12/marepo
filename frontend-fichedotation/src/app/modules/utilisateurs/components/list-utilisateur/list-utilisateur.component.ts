import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AlertService } from 'src/app/shared/helpers/others/alert.service';
import Swal from 'sweetalert2';
import { ECodeProfil, UtilisateurDTO } from '../../models/user.model';
import { UtilisateursService } from '../../services/utilisateurs.service';
import * as XLSX from 'xlsx';
import {
  EResponseStatut,
  ResponseApi,
} from 'src/app/shared/models/response-api.model';
import { StaticDataService } from 'src/app/shared/services/static-data.service';
import * as removeAccents from 'remove-accents';
import { Subject } from 'rxjs';
import { CredentialsService } from 'src/app/modules/authentification/services/credentials.service';
@Component({
  selector: 'app-list-utilisateur',
  templateUrl: './list-utilisateur.component.html',
  styleUrls: ['./list-utilisateur.component.css'],
})
export class ListUtilisateurComponent implements OnInit {
  [x: string]: any;

  page: number = 1;
  pageSize: number = 10;
  filter: string = '';
  collectionSize!: number;
  utilisateurs!: UtilisateurDTO[];
  usersFile: File;
  usersJsonFromFile: string;
  personnels: any[];
  interimaires: any[];
  text = '';
  entities: any[];
  profils: any[];
  idEntitie?: number;
  idProfil?: number;


  constructor(
    private _userService: UtilisateursService,
    private _credentialsService: CredentialsService,
    private _alertService: AlertService,
    private _staticDataService: StaticDataService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getUsers();
    this._staticDataService.allEntites().subscribe({
      next: (response: ResponseApi) => {
        this.entities = response.payload;
      },
    });
    this._staticDataService.allProfils().subscribe({
      next: (response: ResponseApi) => {
        this.profils = response.payload;
      },
    });
  }

  getUsers(): void {
    this._userService
      .listUtilisateur(this.page - 1, this.pageSize, this.filter)
      .subscribe({
        next: (data) => {
          if (data?.status === 'OK') {
            //Pas besoin d'afficher l'utilisateur qui est connecté dans la liste des utilisateurs
            this.utilisateurs = data.payload.filter((utilisateur: UtilisateurDTO) => utilisateur.email !== this._credentialsService.username);
            this.collectionSize = data.metadata!.totalElements;
          } else {
            this._alertService.showAlert({
              status: data?.status,
              message: data?.message,
              titre: 'Utilisateurs',
            });
          }
        },
        error: (error) => console.error(error),
      });
  }

  addUser(): void {
    this.router.navigate(['/gestion-des-utilisateurs/nouvel-utilisateur']);
  }

  onFileLoad(event): void {
    this.usersFile = event.target.files[0];
    const fileReader = new FileReader();
    fileReader.readAsBinaryString(this.usersFile);
    fileReader.onload = (event) => {
      let binaryData = event.target?.result;
      let workBook = XLSX.read(binaryData, { type: 'binary' });

      if (!workBook.SheetNames.includes('EFFECTIF')) {
        Swal.fire({
          icon: 'error',
          title: 'Format du fichier',
          text: 'Une erreur est survenu lors du traitement du fichier !',
          confirmButtonColor: '#1C5EAB',
          confirmButtonText: "D'accord",
        });
      } else {
        this.personnels = XLSX.utils
          .sheet_to_json(workBook.Sheets[workBook.SheetNames[0]])
          .slice(1);
        this.interimaires = XLSX.utils
          .sheet_to_json(workBook.Sheets[workBook.SheetNames[1]])
          .slice(1);

        this.personnels = this.personnels.map((personnel) => {
          this.getIdEntityByEntityName(
            removeAccents(personnel['__EMPTY_2'].toUpperCase())
          );
          this.getIdProfilByProfilName(
            removeAccents("personnel".toUpperCase())
          );
          return {
            matricule: personnel['LISTE PERSONNEL'],
            nom: personnel['__EMPTY'],
            prenom: personnel['__EMPTY_1'],
            email: personnel['__EMPTY_3'],
            linkedEntite: this.idEntitie,
            linkedProfil: this.idProfil,
          };
        });

        Swal.fire({
          title: 'Êtes-vous sûr de faire cet action ?',
          text: 'Confirmez pour ajouter les utilisateurs dans la base de données !',
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#1C5EAB',
          cancelButtonColor: '#FF4D4F',
          confirmButtonText: 'Confirmer',
          cancelButtonText: 'Annuler',
        }).then((result) => {
          if (result.isConfirmed) {
            this._userService
              .saveUtilisateursFromExcel(this.personnels)
              .subscribe({
                next: (response: ResponseApi) => {
                  if (response.status === EResponseStatut.OK) {
                    this._alertService.showAlert({
                      status: response.status,
                      message: response.message,
                      titre: 'Importation des utilisateurs',
                    });
                    this.getUsers();
                  } else if (response.status === EResponseStatut.EXCEPTION) {
                    this._alertService.showAlert({
                      status: 'WARNING',
                      message: response.message,
                      titre: 'Importation des utilisateurs',
                    });
                  }
                },
                error: (error) => {
                  console.error(error);
                },
              });
          }
        });
      }
    };
  }

  getIdEntityByEntityName(libelle: string): void {
    const entityFound = this.entities.find(
      (entity) =>
        removeAccents(entity.libelle.toUpperCase()) === libelle
    );
    this.idEntitie = entityFound.id;
  }

  getIdProfilByProfilName(libelle: string): void {
    const profilFound = this.profils.find(
      (profil) =>
        removeAccents(profil.libelle.toUpperCase()) === libelle
    );
    this.idProfil = profilFound.id;
  }

  changeStatus(user: UtilisateurDTO): void {
    Swal.fire({
      title: 'Êtes-vous sûr de faire cet action ?',
      text: 'Vous ne pourrez pas revenir en arrière !',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#1C5EAB',
      cancelButtonColor: '#FF4D4F',
      confirmButtonText: 'Confirmer',
      cancelButtonText: 'Annuler',
    }).then((result) => {
      if (result.isConfirmed) {
        this._userService.changeStatus(user.id!).subscribe({
          next: () => {
            if (user.status) {
              this.text = 'Bloqué';
              user.status = false;
            } else {
              this.text = 'Débloqué';
              user.status = true;
            }
            Swal.fire({
              title: this.text,
              text: `L'utilisateur ${user.prenom + ' ' + user.nom} a été ${
                this.text
              }.`,
              icon: 'success',
              timer: 2000,
              showCancelButton: false,
              showConfirmButton: false,
            });
          },
        });
      }
    });
  }
}
