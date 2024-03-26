import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SecurityContextService } from '../security-context.service';

@Injectable()
export class ApiTokenInterceptor implements HttpInterceptor {

  constructor(private security:SecurityContextService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let request = req

    if(this.security.loginUser()) {
      request = req.clone({headers: req.headers.append('Authorization', this.security.loginUser()?.token || '')})
    }

    return next.handle(request)
  }
}
