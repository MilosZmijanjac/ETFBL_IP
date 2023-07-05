import { Injectable } from '@angular/core';
import { BehaviorSubject} from 'rxjs';
import { CookieService } from 'ngx-cookie-service';
import { Product } from '../models/domains/Product';
import { CartItem } from '../models/domains/CartItem';

@Injectable({
  providedIn: 'root'
})
export class CartService {


  public cartItemList: CartItem[] = []
  public productList = new BehaviorSubject<any>([]);

  constructor(private cookieService: CookieService) { }
  private getLocalCart(): CartItem[] {
    if (this.cookieService.check('cart')) {
      this.cartItemList = JSON.parse(this.cookieService.get('cart'));
      return Object.values(this.cartItemList);
    } else {
      this.cartItemList = [];
      return [];
    }
  }
  getProducts() {
    const cartItems = this.getLocalCart();
    this.productList.next(cartItems);
    return this.productList.asObservable();
  }
  newCartItem = (product: Product): CartItem => ({
    id: product.id,
    price: product.price,
    count: 1,
    title: product.title,
    image: product.imagesPath
  });
  addToCart(product: Product) {
    let newCartItem = this.newCartItem(product);
    let index = this.cartItemList.map(a => a.id).indexOf(newCartItem.id);

    if (index !== -1)
      this.cartItemList[index].count++;
    else
      this.cartItemList.push(newCartItem);

    this.productList.next(this.cartItemList);
    this.cookieService.set('cart', JSON.stringify(this.cartItemList));
  }
  getTotalPrice(): number {
    let grandTotal = 0;
    this.cartItemList.map((a: CartItem) => {
      grandTotal += a.price * a.count;
    })
    return grandTotal;
  }
  removeCartItem(product: CartItem) {
    let index = this.cartItemList.map(a => a.id).indexOf(product.id);
    if (this.cartItemList[index].count === 1)
      this.cartItemList.splice(index, 1);
    else
      this.cartItemList[index].count--;

    this.productList.next(this.cartItemList);
    this.cookieService.set('cart', JSON.stringify(this.cartItemList));
  }
  removeAllCart() {
    this.cartItemList = []
    this.productList.next(this.cartItemList);
    this.cookieService.delete('cart');
  }

}