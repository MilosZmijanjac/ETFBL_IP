import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { cartItem, product } from '../../data-type';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  public products : cartItem[] = [];
  public grandTotal : number=0;
  constructor(private cartService : CartService,private userService:UserService,
    private router:Router) { }

  ngOnInit(): void {
    this.cartService.getProducts()
    .subscribe(res=>{
      this.products = res;
      this.grandTotal = this.cartService.getTotalPrice();
    })
  }
  checkout(){
   if(!this.userService.isLoggedIn())
   this.router.navigate(['/user-auth']);
  }
  removeItem(item: any){
    this.cartService.removeCartItem(item);
  }
  emptycart(){
    this.cartService.removeAllCart();
  }

}