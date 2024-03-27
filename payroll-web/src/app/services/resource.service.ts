import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ApiResponse } from '../model/api-response';

const BASE_URL = `${environment.baseUrl}/position`

@Injectable({
  providedIn: 'root'
})
export class ResourceService {

  constructor(private http:HttpClient) { }

  search() {
    return this.http.get<ApiResponse>(BASE_URL)
  }

  findById(id:string) {
    return this.http.get<ApiResponse>(`${BASE_URL}/${id}`)
  }

  save(edit:boolean, form:any) {
    if(edit) {
      const {id, ...updateForm} = form
      return this.http.put(`${BASE_URL}/${id}`, updateForm)
    }
    return this.http.post(BASE_URL, form)
  }

}
