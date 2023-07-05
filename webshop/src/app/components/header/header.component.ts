import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { LOCALSTORAGE_TOKEN_KEYS, LOCALSTORAGE_USER_KEYS } from 'src/app/app.module';
import { TokenModel } from 'src/app/models/domains/TokenModel';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { UserInfoResponse } from 'src/app/models/responses/UserInfoResponse';
import { NgForm } from '@angular/forms';
import { ProductService } from 'src/app/services/product.service';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  
  public totalItem: number = 0;
  public username: string = 'Account';
  public isLoggedIn: boolean = false;
  userInfo: UserInfoResponse | any;
  constructor(private cartService: CartService,private productService:ProductService, private jwtHelperService: JwtHelperService, private router: Router, private userService: UserService) { }
  
  ngOnInit(): void {
    let tokenString: string | null = localStorage.getItem(LOCALSTORAGE_TOKEN_KEYS);
    if (tokenString !== null) {
      let tokens: TokenModel = JSON.parse(tokenString);

      this.username = this.jwtHelperService.decodeToken(tokens.access_token).sub;
      let userString: string | null = localStorage.getItem(LOCALSTORAGE_USER_KEYS);
      if (userString === null) {
        this.userService.getUserInfo(this.username).subscribe(res => {
          this.userInfo = res;
          localStorage.setItem(LOCALSTORAGE_USER_KEYS, JSON.stringify(this.userInfo));
          this.isLoggedIn = true;
        });
      }else{
        this.userInfo=JSON.parse(userString);
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
  filter2Products(data: NgForm): void {
    console.log("search: {"+data.value.search+"}")
    this.productService.setSearch(data.value.search);
    this.router.navigate(['loading']).then(() => {
      this.router.navigate(['products']).then(() => {
      
      });
    });
  
  }
}
