import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { SecurityContextService } from '../security-context.service';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {

  const security = inject(SecurityContextService)

  let request = req

  if(security.loginUser()) {
    request = req.clone({headers: req.headers.append('Authorization', security.loginUser()?.token || '')})
  }
  return next(request);
};
