import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS, provideHttpClient, withFetch, withInterceptorsFromDi } from '@angular/common/http';
import { ApiTokenInterceptor } from './services/interceptors/api-token.interceptor';

const httpInterceptors = [
  {provide: HTTP_INTERCEPTORS, useClass: ApiTokenInterceptor, multi: true}
]

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideClientHydration(),
    httpInterceptors,
    provideHttpClient(withFetch(), withInterceptorsFromDi()),
  ]
};

