import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { UserService } from '../../services/user.service';
import { Router } from '@angular/router';
import { OrdersService } from 'src/app/services/orders.service';
import { CartItem } from 'src/app/models/domains/CartItem';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  options = [
    { label: 'CREDIT_CARD', value:"CREDIT_CARD" },
    { label: 'DEBIT_CARD', value:"DEBIT_CARD" },
    { label: 'PAYPAL', value:"PAYPAL" },
    { label: 'GOOGLE_PAY', value:"GOOGLE_PAY" },
    { label: 'APPLE_PAY', value:"APPLE_PAY" },
    { label: 'CASH', value: "CASH" }
  ];
  public products: CartItem[] = [];
  public grandTotal: number = 0;
  public paymentMethod:string="";
  constructor(private cartService: CartService, private userService: UserService, private orderService: OrdersService,
    private router: Router) { }

  ngOnInit(): void {
    this.cartService.getProducts()
      .subscribe(res => {
        this.products = res;
        this.grandTotal = this.cartService.getTotalPrice();
      })
  }
  checkout():void {
    if (!this.userService.isLoggedIn()){
      this.router.navigate(['/user-auth']);
      return;
    }    
      
    this.orderService.createOrder(this.products, this.paymentMethod, this.grandTotal).subscribe(
      data => this.emptycart()
    );
  }
  removeItem(item: any) {
    this.cartService.removeCartItem(item);
  }
  emptycart() {
    this.cartService.removeAllCart();
  }

}