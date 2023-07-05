import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import{switchMap} from 'rxjs/operators';
import { LOCALSTORAGE_TOKEN_KEYS } from '../app.module';
import { JwtHelperService } from '@auth0/angular-jwt';
import { UserService } from '../services/user.service';
import { Router } from '@angular/router';
import { TokenModel } from '../models/domains/TokenModel';

@Injectable()
export class AuthTokenInterceptor implements HttpInterceptor {

  constructor(
    private jwtHelper: JwtHelperService,
    private userService: UserService,
    private router: Router
  ) {}
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const localStorageTokens = localStorage.getItem(LOCALSTORAGE_TOKEN_KEYS);
    if (req.url.indexOf('login') > -1 ||req.url.indexOf('register') > -1||localStorageTokens===null) {
      return next.handle(req);
    }
   
    if ( req.url.indexOf('refresh') > -1) {
      req = req.clone({ headers: req.headers.set('Authorization', 'Bearer ' + this.userService.getRefreshToken()) });
      return next.handle(req);
    }
    
    var token: TokenModel;
    if (localStorageTokens) {
      token = JSON.parse(localStorageTokens) as TokenModel;
      var isTokenExpired = this.jwtHelper.isTokenExpired(this.userService.getAccessToken());
      if (!isTokenExpired) {
        return next.handle(req);
      } else {
        return this.userService.refreshToken().pipe(
          switchMap((newTokens: TokenModel) => {
            localStorage.setItem(LOCALSTORAGE_TOKEN_KEYS, JSON.stringify(newTokens));
            req = req.clone({ headers: req.headers.set('Authorization', 'Bearer ' + newTokens.access_token) });
            return next.handle(req);
          })
        );
      }
    }
    this.router.navigate(['/']);
    return throwError(() => 'Invalid call');

  }
 
}


