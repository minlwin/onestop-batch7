import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiResponse } from './api-response';
import { environment } from '../../environments/environment';

const BASE_URL = `${environment.baseUrl}/position`

@Injectable({
  providedIn: 'root'
})
export class PositionService {

  constructor(private http:HttpClient) { }

  search(form:any) {
    return this.http.get<ApiResponse>(BASE_URL, {params: form})
  }

  findById(id:string) {
    return this.http.get<ApiResponse>(`${BASE_URL}/${id}`)
  }
}
