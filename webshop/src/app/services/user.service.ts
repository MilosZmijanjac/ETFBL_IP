import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { RegistrationRequest } from '../models/requests/RegistrationRequest';
import { LoginResponse } from '../models/responses/LoginResponse';
import { LOCALSTORAGE_TOKEN_KEYS, LOCALSTORAGE_USER_KEYS } from '../app.module';
import { TokenModel } from '../models/domains/TokenModel';
import { PinRequest } from '../models/requests/PinRequest';
import { PinResponse } from '../models/responses/PinResponse';
import { UserInfoResponse } from '../models/responses/UserInfoResponse';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private cookieService: CookieService,private http: HttpClient) { }

  isLoggedIn():boolean{
    return this.cookieService.check('user');
  }
   register(request : RegistrationRequest){
    return this.http.post<any>("http://localhost:8080/api/user/register", request);
  }
  checkPin(request : PinRequest){
    
    return this.http.post<PinResponse>("http://localhost:8080/api/user/pin", request);
  }
  login(username:string, password:string){
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/form-data');
    const options = { headers };
    const params = new HttpParams()
      .set('username', username)
      .set('password', password);
    return this.http.get<LoginResponse>("http://localhost:8080/api/user/login",{params,...options});
  }
  logout(){
    localStorage.removeItem(LOCALSTORAGE_TOKEN_KEYS);
    localStorage.removeItem(LOCALSTORAGE_USER_KEYS);
  }
  getUserInfo(username:string){
    return this.http.get<UserInfoResponse>("http://localhost:8080/api/user/info/"+username);
  }
  getAccessToken(): string {
    var localStorageToken = localStorage.getItem(LOCALSTORAGE_TOKEN_KEYS);
    if (localStorageToken) {
      var tokens = JSON.parse(localStorageToken) as TokenModel;
      return tokens.access_token;
    }
    return '';
  }
  
}
