import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { LOCALSTORAGE_TOKEN_KEYS,LOCALSTORAGE_USER_KEYS } from 'src/app/app.module';
import { TokenModel } from 'src/app/models/domains/TokenModel';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { UserInfoResponse } from 'src/app/models/responses/UserInfoResponse';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public totalItem: number = 0;
  public username: string = 'Account';
  public isLoggedIn: boolean = false;
  userInfo:UserInfoResponse|any;
  constructor(private cartService: CartService, private jwtHelperService: JwtHelperService, private router: Router, private userService: UserService) { }

  ngOnInit(): void {
    let tokenString: string | null = localStorage.getItem(LOCALSTORAGE_TOKEN_KEYS);
    if (tokenString !== null) {
      let tokens: TokenModel = JSON.parse(tokenString);
      this.username = this.jwtHelperService.decodeToken(tokens.access_token).sub;
      let userString:string|null=localStorage.getItem(LOCALSTORAGE_USER_KEYS);
      if(userString===null){
        this.userService.getUserInfo(this.username).subscribe( res=>{this.userInfo=res;localStorage.setItem(LOCALSTORAGE_USER_KEYS,JSON.stringify(this.userInfo));});
        
        this.isLoggedIn = true;
      }
    }

    this.cartService.getProducts()
      .subscribe(res => {
        this.totalItem = res.length;
      })
  }
  logout() {
    this.userService.logout();
    this.router.navigate(['cart']).then(() => {
      window.location.reload();
    });
  }
}
