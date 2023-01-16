import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import jwtDecode from 'jwt-decode';
import {ActivatedRoute, Router} from '@angular/router';
import {AuthResponseData} from '../models/auth-response-data';
import {User} from '../models/auth-user.model';
import {environment} from '../../../../environments/environment';
import {CredentialsService} from './credentials.service';
import {ResponseApi} from '../../../@shared/models/response-api.model';
import {AlertService} from '../../../@shared/helpers/others/alert.service';

const TOKEN_KEY = 'FACTORY_STARTER_KIT';
const TIME_MILLIS_BEFORE_LOGOUT = 30 * 1000;

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  timeoutInterval: any;
  logoutInterval: any;

  constructor(
    private http: HttpClient,
    private credentialsService: CredentialsService,
    private router: Router,
    private route: ActivatedRoute,
    private alertService: AlertService
  ) {
  }

  signin = (credentials: any): Observable<ResponseApi> => this.http.post<ResponseApi>(`${environment.apiUrl}/auth/signin`, credentials);

  signup$ = (data: any): Observable<ResponseApi> => this.http.post<ResponseApi>(`${environment.apiUrl}/auth/signup`, data);
  authenticateUserWithFirstUrlConnexion = (credentials: any): Observable<ResponseApi> => this.http.post<ResponseApi>(`${environment.apiUrl}/auth/signinWithFirstUrlConnexion`, credentials);
  authenticateUserWithForgetPasswordUrlConnexion = (credentials: any): Observable<ResponseApi> => this.http.post<ResponseApi>(`${environment.apiUrl}/auth/signinWithForgetPasswordUrlConnexion`, credentials);
  handleForgetPassword = (login: string): Observable<any> => this.http.get(`${environment.apiUrl}/auth/forgotPassword?login=${login}`);
  editUserInfos = (infos: any): Observable<ResponseApi> => this.http.post<ResponseApi>(`${environment.apiUrl}/auth/editUserInfos`, infos);
  editUserPassword = (infos: any): Observable<ResponseApi> => this.http.post<ResponseApi>(`${environment.apiUrl}/auth/editUserPassword`, infos);

  formatUser(data: AuthResponseData): User {
    const refToken: any = jwtDecode(data?.token);
    const expirationDate = new Date(refToken.exp * 1000);
    return new User(data?.username, data?.token, data?.authorities, expirationDate);
  }
  runTimeoutInterval(user: User): void {
    const todaysDate = new Date().getTime();
    const expirationDate = user.expireDate.getTime();
    const timeInterval = (expirationDate - todaysDate) - TIME_MILLIS_BEFORE_LOGOUT;
    this.clearTimeoutInterval();

    this.timeoutInterval = setTimeout(() => {
      this.logout(false);
    }, timeInterval);
  }
  clearTimeoutInterval(): void {
    if (this.timeoutInterval) {
      clearInterval(this.timeoutInterval);
      clearTimeout(this.timeoutInterval);
      this.timeoutInterval = null;
    }

    if (this.logoutInterval) {
      clearInterval(this.logoutInterval);
      clearTimeout(this.logoutInterval);
      this.logoutInterval = null;
    }
  }
  getUserFromLocalStorage(): User {
    const userDataString = sessionStorage.getItem(TOKEN_KEY);
    if (userDataString) {
      const userData = JSON.parse(userDataString);
      const expirationdate = new Date(userData.expirationDate);
      return new User(userData.username, userData.accessToken, userData.authorities, expirationdate);
    }
    return null;
  }
  logout(immediateLogout: boolean = true): void {
    if (immediateLogout) {
      this.onLogout();
    } else {
      this.alertService.showAlert({
        status: 'WARNING',
        message: 'En cas d\'inactivité sur la plateforme, vous serez déconnecté dans 30 secondes !',
        titre: 'Informations'
      });
      this.logoutInterval = setTimeout(() => {
        this.onLogout();
      }, TIME_MILLIS_BEFORE_LOGOUT);
    }
  }
  onLogout(): void {
    this.credentialsService.setCredentials();
    this.router.navigate([this.route.snapshot.queryParams.redirect || '/auth/login'], {replaceUrl: true});
  }
  saveUserCredentials(data: AuthResponseData, remember?: boolean): void {
    this.credentialsService.setCredentials({accessToken: data?.token, username: data?.username, authorities: data?.authorities}, remember);
    this.runTimeoutInterval(this.formatUser(data));
  }
  refreshUserCredentials(data: AuthResponseData): void {
    this.credentialsService.refreshCredentials({accessToken: data?.token, username: data?.username, authorities: data?.authorities});
    this.runTimeoutInterval(this.formatUser(data));
  }

}
