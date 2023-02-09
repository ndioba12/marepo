import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ResponseApi } from '../../../shared/models/response-api.model';
import { UtilisateurDTO } from '../models/user.model';
import { IUpdatePasswordDTO } from '../models/updatePassword.model';

@Injectable({
  providedIn: 'root',
})
export class UtilisateursService {
  constructor(private _http: HttpClient) {}

  saveUtilisateur(dto: UtilisateurDTO): Observable<ResponseApi> {
    return this._http.post<ResponseApi>(
      `${environment.apiUrl}/saveUtilisateur`,
      dto
    );
  }

  saveUtilisateursFromExcel(users: UtilisateurDTO[]): Observable<ResponseApi> {
    return this._http.post<ResponseApi>(
      `${environment.apiUrl}/saveUsersFromExcel`,
      users
    )
  }

  listUtilisateur(
    page: number,
    size: number,
    filter: string
  ): Observable<ResponseApi> {
    return this._http.get<ResponseApi>(
      `${environment.apiUrl}/listUtilisateur?page=${page}&size=${size}&filter=${filter}`
    );
  }

  getUtilisateur(id: number): Observable<ResponseApi> {
    return this._http.get<ResponseApi>(
      `${environment.apiUrl}/listUtilisateur/${id}`
    );
  }

  editUtilisateur(id: number, dto: UtilisateurDTO): Observable<ResponseApi> {
    return this._http.put<ResponseApi>(
      `${environment.apiUrl}/editUtilisateur/${id}`,
      dto
    );
  }

  updatePasswordLoggedUser(updatePasswordForm: IUpdatePasswordDTO): Observable<ResponseApi> {
    return this._http.put<ResponseApi>(
      `${environment.apiUrl}/updatePassword`,
      updatePasswordForm
    )
  }

  deleteUtilisateur(id: number): Observable<ResponseApi> {
    return this._http.delete<ResponseApi>(
      `${environment.apiUrl}/deleteUtilisateur/${id}`
    );
  }

  changeStatus(id: number): Observable<ResponseApi> {
    return this._http.put<ResponseApi>(
      `${environment.apiUrl}/changeStatus/${id}`,
      null
    );
  }
}
