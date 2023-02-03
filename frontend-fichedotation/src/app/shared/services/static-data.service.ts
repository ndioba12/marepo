import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ResponseApi } from '../models/response-api.model';

@Injectable({
  providedIn: 'root',
})
export class StaticDataService {
  entites$ = this.http.get<ResponseApi>(
    `${environment.apiUrl}/static/listEntite`
  );
  profils$ = this.http.get<ResponseApi>(
    `${environment.apiUrl}/static/listProfil`
  );

  constructor(private http: HttpClient) {}

  allProfils = (): Observable<ResponseApi> =>
    this.http.get<ResponseApi>(`${environment.apiUrl}/profils`);

  allEntites = (): Observable<ResponseApi> =>
    this.http.get<ResponseApi>(`${environment.apiUrl}/entites`);
    allTypeMateriels = (): Observable<ResponseApi> =>
    this.http.get<ResponseApi>(`${environment.apiUrl}/listTypeMateriels`);
  
    allCatMateriels = (): Observable<ResponseApi> =>
    this.http.get<ResponseApi>(`${environment.apiUrl}/listCatMateriels`);
}
