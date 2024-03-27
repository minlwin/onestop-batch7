import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { ApiResponse } from '../model/api-response';

const BASE_URL = `${environment.baseUrl}/position/code`

@Injectable({
  providedIn: 'root'
})
export class PositionCodeService {

  constructor(private http:HttpClient) { }

  getAll() {
    return this.http.get<ApiResponse>(BASE_URL)
  }

  findForCreate(department:string) {
    return this.http.get<ApiResponse>(`${BASE_URL}/${department}`)
  }

  findForUse(department:string) {
    return this.http.get<ApiResponse>(`${BASE_URL}/${department}/employee`)
  }
}
