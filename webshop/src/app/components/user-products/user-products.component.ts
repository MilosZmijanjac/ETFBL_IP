import { Component, OnInit } from '@angular/core';
import { Product } from 'src/app/models/domains/Product';
import { CartService } from 'src/app/services/cart.service';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-products',
  templateUrl: './user-products.component.html',
  styleUrls: ['./user-products.component.css']
})
export class UserProductsComponent implements OnInit {
  
  products: Product[]=[];
  filterProducts: Product[]=[];

  constructor(private userService:UserService,private productService:ProductService,private cartService : CartService) { }

  ngOnInit(): void {
      this.productService.userProductList(this.userService.getUsername()).subscribe((data)=>{
      this.products=data;
      this.filterProducts=data;
    })
  }
  addToCart(item: Product){
      this.cartService.addToCart(item);
    console.log(item) 
  }
  filter(eventData: {status:string}){
    this.filterProducts=this.products;
    if(eventData.status!=="ALL")
    this.filterProducts=this.filterProducts?.filter(p=>p.status==eventData.status);
  }
  onScroll(): void {
    /* this.trendyProducts?.push(this.trendyProducts[1])
          console.log(this.trendyProducts?.length)*/
  }
  status: boolean = false;
  clickEvent(){
      this.status = !this.status;       
  }
}
