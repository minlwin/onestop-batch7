import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ApiResponse } from '../model/api-response';

const BASE_URL = `${environment.baseUrl}/employee`

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http:HttpClient) { }

  search(form:any) {
    return this.http.get<ApiResponse>(BASE_URL, {params: form})
  }

  findById(code:string) {
    return this.http.get<ApiResponse>(`${BASE_URL}/${code}`)
  }

  findByIdForUpdate(code:string) {
    return this.http.get<ApiResponse>(`${BASE_URL}/${code}/update`)
  }

  create(form:any) {
    console.log(form)
    return this.http.post<ApiResponse>(BASE_URL, form)
  }

  updateInfo(code:string, form:any) {
    return this.http.put<ApiResponse>(`${BASE_URL}/${code}`, form)
  }

  updateStatus(code:string, form:any) {
    return this.http.put<ApiResponse>(`${BASE_URL}/${code}/status`, form)
  }

  updatePosition(code:string, form:any) {
    return this.http.put<ApiResponse>(`${BASE_URL}/${code}/position`, form)
  }

}
