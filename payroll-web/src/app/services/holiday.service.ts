import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { ApiResponse } from '../model/api-response';

const BASE_URL = `${environment}/holiday`

@Injectable({
  providedIn: 'root'
})
export class HolidayService {

  constructor(private http:HttpClient) { }

  search(form:any) {
    return this.http.get<ApiResponse>(BASE_URL, {params: form})
  }

  findById(id:string) {
    return this.http.get<ApiResponse>(`${BASE_URL}/${id}`)
  }

  save(edit:boolean, form:any) {
    if(edit) {
      const {date, ...updateForm} = form
      return this.http.put(`${BASE_URL}/${date}`, updateForm)
    }
    return this.http.post(BASE_URL, form)
  }
}
