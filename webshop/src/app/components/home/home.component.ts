import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from 'src/app/models/domains/Product';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
 popularProducts:Product[]=[];
  constructor(private product:ProductService) {}

  ngOnInit(): void {
    this.product.popularProductList().subscribe(data=>{
      this.popularProducts=data;
    })
  }
}
