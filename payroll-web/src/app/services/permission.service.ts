import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ApiResponse } from '../model/api-response';

const BASE_URL = `${environment.baseUrl}/permission`

@Injectable({
  providedIn: 'root'
})
export class PermissionService {

  constructor(private http:HttpClient) { }

  save(form:any) {
    return this.http.put<ApiResponse>(BASE_URL, form)
  }
}
