import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { ApiResponse } from './api-response';

const BASE_URL = `${environment.baseUrl}/department`

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  constructor(private http:HttpClient) { }

  save(edit:boolean, form:any) {
    if(edit) {
      const {code, ...updateForm} = form
      return this.http.put<ApiResponse>(`${BASE_URL}/${code}`, updateForm)
    }

    return this.http.post<ApiResponse>(BASE_URL, form)
  }

  search(form:any) {
    return this.http.get<ApiResponse>(BASE_URL, {params: form})
  }

  findById(code:string) {
    return this.http.get<ApiResponse>(`${BASE_URL}/${code}`)
  }

  updateManager() {

  }
}
