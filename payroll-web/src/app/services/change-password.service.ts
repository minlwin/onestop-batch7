import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';

const BASE_URL = `${environment.baseUrl}/personal/password`

@Injectable({
  providedIn: 'root'
})
export class ChangePasswordService {

  constructor(private http:HttpClient) { }

  changePassword(form:any) {
    return this.http.post(BASE_URL, form)
  }
}
