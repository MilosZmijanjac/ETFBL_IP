import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/models/domains/Order';
import { OrdersService } from 'src/app/services/orders.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  orders: Order[] = [];
  orderItems: any;
  constructor(private ordersService: OrdersService, private userService: UserService) { }

  ngOnInit(): void {
    this.ordersService.getOrders(this.userService.getUsername()).subscribe(data => this.orders = data);
  }
  selectOrder(id: any) {
    this.ordersService.getOrderItems(id).subscribe(data => this.orderItems = data);
  }

}
