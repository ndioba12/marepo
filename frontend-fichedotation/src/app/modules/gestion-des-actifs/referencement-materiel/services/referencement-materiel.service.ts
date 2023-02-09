import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseApi } from 'src/app/shared/models/response-api.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReferencementMaterielService {

  constructor(private _http: HttpClient) {}
  listImmoReference(
    page: number,
    size: number,
    filter: string
  ): Observable<ResponseApi> {
    return this._http.get<ResponseApi>(
      `${environment.apiUrl}/listImmoReference?page=${page}&size=${size}&filter=${filter}`
    );
  }

}
