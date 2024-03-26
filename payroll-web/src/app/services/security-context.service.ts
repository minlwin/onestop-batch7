import { Injectable, effect, signal } from '@angular/core';

const LOGIN_USER_KEY = "com.jdc.payroll.user"

@Injectable({
  providedIn: 'root'
})
export class SecurityContextService {

  loginUser = signal<User>(undefined)

  constructor() {
    try {
      const storedUser = localStorage.getItem(LOGIN_USER_KEY)

      if(storedUser) {
        this.loginUser.set(JSON.parse(storedUser))
      }

      effect(() => {
        if(this.loginUser()) {
          localStorage.setItem(LOGIN_USER_KEY, JSON.stringify(this.loginUser()))
        } else {
          localStorage.removeItem(LOGIN_USER_KEY)
        }
      })
    } catch (error) {
    }
  }

}

type User = LoginUser | undefined

export interface LoginUser {
  readonly loginId:string
  readonly name:string
  readonly authorities:string[]
  readonly token:string
  readonly activated:boolean
  [name:string]:any
}
