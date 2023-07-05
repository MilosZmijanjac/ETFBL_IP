import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { CartService } from 'src/app/services/cart.service';
import { Product } from 'src/app/models/domains/Product';
import { UserService } from 'src/app/services/user.service';
import { UserInfoResponse } from 'src/app/models/responses/UserInfoResponse';
import {Location} from '@angular/common';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {
  productData!: Product;
  productQuantity: number = 1;
  removeCart = false;
  productId!: number | any
  userInfo: UserInfoResponse | any = null;
  sellerInfo: UserInfoResponse | any = {};
  constructor(private activeRoute: ActivatedRoute,private location:Location, private product: ProductService, private cartService: CartService, private userService: UserService) { }

  ngOnInit(): void {
    this.productId = this.activeRoute.snapshot.paramMap.get('productId');
    this.userService.getSellerInfo(this.productId).subscribe(data => this.sellerInfo = data);
    this.userInfo = this.userService.getLocalUserInfo();
    console.warn(this.productId);
    this.productId && this.product.getProduct(this.productId).subscribe((result) => {
      this.productData = result;
    });

  }


  addToCart() {
    this.cartService.addToCart(this.productData);

  }
  deleteProduct() {
    this.product.deleteProduct(this.productData.id).subscribe();
    this.location.back();
  }
  endProduct() {
    console.log("end")
    this.product.endProduct(this.productData.id).subscribe(data=>this.location.back());

  }
  activateProduct() {
    console.log("start")
    this.product.activateProduct(this.productData.id).subscribe(data=>this.location.back());

  }
}
