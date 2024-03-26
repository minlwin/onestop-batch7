import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SecurityContextService {

  loginUser?:LoginUser

  constructor() { }
}

export interface LoginUser {
  readonly loginId:string
  readonly name:string
  readonly authorities:string[]
  readonly token:string
  readonly activated:boolean
  [name:string]:any
}
