import {ActivatedRoute, Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {UtilisateurDTO} from '../../models/user.model';
import {UtilisateursService} from '../../services/utilisateurs.service';
import {AlertService} from '../../../../@shared/helpers/others/alert.service';

@Component({
  selector: 'app-detail-utilisateur',
  templateUrl: './detail-utilisateur.component.html',
  styleUrls: ['./detail-utilisateur.component.css']
})
export class DetailUtilisateurComponent implements OnInit {

  form: FormGroup;
  utilisateur: UtilisateurDTO;

  constructor(
    private fb: FormBuilder,
    private activatedRoute: ActivatedRoute,
    private route: Router,
    private utilisateursService: UtilisateursService,
    private alertService: AlertService
  ) {
  }

  ngOnInit(): void {

    this.form = this.fb.group({
      prenom: [],
      nom: [],
      email: [],
      telephone: [],
      adresse: [],
      linkedProfil: []
    });


    const id = this.activatedRoute.snapshot.paramMap.get('id') || null;
    // tslint:disable-next-line:radix
    this.utilisateursService.getUtilisateur(parseInt(id)).subscribe(rsp => {
      if (rsp?.status === 'OK') {
        this.utilisateur = rsp?.payload;
        this.form.patchValue({
          prenom: this.utilisateur?.prenom,
          nom: this.utilisateur?.nom,
          email: this.utilisateur?.email,
          telephone: this.utilisateur?.telephone,
          adresse: this.utilisateur?.adresse ?? '----------',
          linkedProfil: this.utilisateur?.linkedProfil?.libelle
        });
      } else {
        this.alertService.showAlert({status: rsp?.status, message: rsp?.message, titre: 'Utilisateurs'});
        this.cancel();
      }
    });
  }

  cancel(): void {
    this.route.navigateByUrl('/users/list');
  }
}
