import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { ApiResponse } from '../model/api-response';

const BASE_URL =`${environment.baseUrl}/public/signin`

@Injectable({
  providedIn: 'root'
})
export class SignInService {

  constructor(private http:HttpClient) { }

  signIn(form:any) {
    return this.http.post<ApiResponse>(BASE_URL, form)
  }
}
