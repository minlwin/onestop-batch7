import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ApiResponse } from '../model/api-response';

const BASE_URL = `${environment.baseUrl}/leave-type`

@Injectable({
  providedIn: 'root'
})
export class LeaveTypeService {

  constructor(private http:HttpClient) { }

  search(form:any) {
    return this.http.get<ApiResponse>(BASE_URL, {params: form})
  }

  findById(id:number) {
    return this.http.get<ApiResponse>(`${BASE_URL}/${id}`)
  }

  save(edit:boolean, form:any) {

    if(edit) {
      const {id, ... updateForm} = form
      return this.http.put<ApiResponse>(`${BASE_URL}/${id}`, updateForm)
    }

    return this.http.post<ApiResponse>(BASE_URL, form)
  }

}
