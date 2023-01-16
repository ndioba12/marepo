import {Injectable} from '@angular/core';
import jwt_decode from 'jwt-decode';

export interface Credentials {
  // Personnalisez les informations d'identification reçues ici
  username: string;
  accessToken: any;
  authorities: any;
}

const credentialsKey = 'FACTORY_STARTER_KIT';

/**
 * Fournit le stockage des informations d'authentification.
 */
@Injectable({
  providedIn: 'root'
})
export class CredentialsService {

  constructor() {
    const savedCredentials = sessionStorage.getItem(credentialsKey) || localStorage.getItem(credentialsKey);
    if (savedCredentials) {
      this._credentials = JSON.parse(savedCredentials);
    }
  }

  private _credentials: Credentials | null = null;

  /**
   * Obtient les informations d'identification de l'utilisateur.
   * @return Les informations d'identification de l'utilisateur ou null si l'utilisateur n'est pas authentifié.
   */
  get credentials(): Credentials | null {
    return this._credentials;
  }

  /**
   * Obtient le token contenu dans informations d'identification de l'utilisateur.
   * @return Le token decodé contenu dans les informations d'identification de l'utilisateur ou null si l'utilisateur n'est pas authentifié.
   */
  get accessToken(): any | null {
    return this._credentials?.accessToken ? jwt_decode(this._credentials?.accessToken) : null;
  }

  get undecodeAccessToken(): any | null {
    return this._credentials?.accessToken ? this._credentials?.accessToken : null;
  }

  /**
   * Obtient le token contenu dans informations d'identification de l'utilisateur.
   * @return Le token decodé contenu dans les informations d'identification de l'utilisateur ou null si l'utilisateur n'est pas authentifié.
   */
  get expirationTime(): number | null {
    return this.accessToken?.exp;
  }

  get userMenus(): any | null {
    return this.accessToken?.menu;
  }

  get userInfos(): any | null {
    return this.accessToken?.infos;
  }

  get fullName(): string {
    return this.userInfos ? this.userInfos?.prenomNom : 'Unknow';
  }

  get username(): string {
    return this.userInfos ? this.userInfos?.linkedProfil?.libelle : 'Unknow';
  }

  get authorities(): string[] {
    let roles = [];
    if (this.isAuthenticated()) {
      this._credentials.authorities.forEach(authority => {
        roles.push(authority.authority);
      });
    }

    return roles;
  }

  get storage(): any {
    return localStorage.getItem(credentialsKey) ? localStorage : sessionStorage;
  }

  /**
   * Vérifie si l'utilisateur est authentifié.
   * @return True si l'utilisateur est authentifié.
   */
  isAuthenticated(): boolean {
    return !!this.credentials;
  }

  /**
   * Vérifie si l'utilisateur est authentifié.
   * @return True si l'utilisateur est authentifié.
   */
  isExpiredToken(): boolean {
    if (this.isAuthenticated()) {
      let currentDate: number = new Date().getTime() / 1000;
      return this.expirationTime ? this.expirationTime < currentDate : true;
    } else {
      return true;
    }
  }

  /**
   * Définit les informations d'identification de l'utilisateur.
   * Les informations d'identification peuvent être conservées entre les sessions en définissant le paramètre `remember` à true.
   * Sinon, les informations d'identification ne sont conservées que pour la session en cours.
   * @param credentials Les informations d'identification de l'utilisateur.
   * @param remember True pour mémoriser les informations d'identification entre les sessions.
   */
  setCredentials(credentials?: Credentials, remember?: boolean) {
    this._credentials = credentials || null;

    if (credentials) {
      const storage = remember ? localStorage : sessionStorage;
      storage.setItem(credentialsKey, JSON.stringify(credentials));
    } else {
      sessionStorage.removeItem(credentialsKey);
      localStorage.removeItem(credentialsKey);
    }
  }

  refreshCredentials(credentials?: Credentials) {
    const storage = this.storage;
    storage.setItem(credentialsKey, JSON.stringify(credentials));
  }

  /**
   * permet de deconnecter le user donc de supprimer ces informations d'authentification
   */
  removeCredentials() {
    sessionStorage.removeItem(credentialsKey);
    localStorage.removeItem(credentialsKey);
  }
}
