import { Component,  OnInit } from '@angular/core';
import { product } from '../../data-type';
import { ProductService } from '../../services/product.service';
import { CartService } from '../../services/cart.service';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  trendyProducts:undefined | product[];
  loading = false;
  constructor(private product:ProductService,private cartService : CartService) { }

  ngOnInit(): void {
      this.product.trendyProducts().subscribe((data)=>{
      this.trendyProducts=data;
    })
  }
  addToCart(item: product){
      this.cartService.addToCart(item);
    console.log(item) 
  }
  onScroll(): void {
     this.trendyProducts?.push(this.trendyProducts[1])
          console.log(this.trendyProducts?.length)
  }
  status: boolean = false;
  clickEvent(){
      this.status = !this.status;       
  }
}
