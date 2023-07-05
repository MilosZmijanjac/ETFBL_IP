import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { OrderRequest } from '../models/requests/OrderRequest';
import { OrderItem } from '../models/domains/OrderItem';
import { UserService } from './user.service';
import { CartItem } from '../models/domains/CartItem';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {
  orders = [];
  constructor(private http: HttpClient,private userService:UserService) { }

  getOrders(username: string) {
    return this.http.get<any>("http://localhost:8080/api/order/" + username);
  }

  getOrderItems(id: number) {
    return this.http.get<any>("http://localhost:8080/api/order/items/" + id);
  }

 createOrder(cartItems:CartItem[],payment:string,total:number){
  let orderItems:OrderItem[]=[];
  cartItems.forEach(item=>{
     orderItems.push({quantity:item.count,price:item.price,productName:item.title});
  })
  let request:OrderRequest={
    username:this.userService.getUsername(),paymentMethod:payment,totalPrice:total,items:orderItems
  }
  return this.http.post<any>("http://localhost:8080/api/order/",request);
 }

 
}
