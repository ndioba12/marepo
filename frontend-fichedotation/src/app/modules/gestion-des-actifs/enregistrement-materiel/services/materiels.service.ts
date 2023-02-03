import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseApi } from 'src/app/shared/models/response-api.model';
import { environment } from 'src/environments/environment';
import { AjoutMaterielAchatDTO } from '../models/materiel.model';

@Injectable({
  providedIn: 'root'
})
export class MaterielsService {

  constructor(private _http: HttpClient) {}
  listImmobilisations(
    page: number,
    size: number,
    filter: string
  ): Observable<ResponseApi> {
    return this._http.get<ResponseApi>(
      `${environment.apiUrl}/listImmobilisations?page=${page}&size=${size}&filter=${filter}`
    );
  }

  saveMateriel(dto: AjoutMaterielAchatDTO): Observable<ResponseApi> {
    return this._http.post<ResponseApi>(
      `${environment.apiUrl}/addImmobilisation`,
      dto
    );
  }
  getMateriel(id: number): Observable<ResponseApi> {
    return this._http.get<ResponseApi>(
      `${environment.apiUrl}/listImmobilisations/${id}`
    );
  }
}
