import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/modules/authentification/services/auth.service';
import { CredentialsService } from 'src/app/modules/authentification/services/credentials.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  userFullname: string = '';
  authoritie!: string;
  dynamicLink: string;

  constructor(
    private _authService: AuthService,
    private _credentialsService: CredentialsService
  ) {
    this.userFullname = this._credentialsService.fullName;

    this.authoritie = this._credentialsService.userAuthorities[0]['authority'];
    switch (this.authoritie) {
      case 'SAD':
        this.dynamicLink = '/gestion-des-utilisateurs'
        break;
      case 'ADM':
        this.dynamicLink = '/gestion-des-utilisateurs'
        break;
      case 'ACH':
        this.dynamicLink = '/gestion-des-actifs/liste-des-materiels-achats'
        break;
      case 'SET':
        this.dynamicLink = '/gestion-des-actifs/liste-des-materiels'
        break;
      case 'SEL':
        this.dynamicLink = '/gestion-des-actifs/liste-des-materiels'
        break;
      case 'PER':
        this.dynamicLink = '/gestion-des-actifs/liste-des-affectations'
        break;
      case 'CTG':
        this.dynamicLink = '/gestion-des-actifs/inventaires'
        break;
      case 'CPT':
        this.dynamicLink = '/gestion-des-actifs/comptable'
        break;
      case 'CSO':
        this.dynamicLink = '/gestion-des-actifs/cessions'
        break;
      default:
        this.dynamicLink = '/dashboard'
        break;
    }
  }

  ngOnInit(): void {}

  //Using of loGout service
  logOut() {
    this._authService.logout();
  }
}
