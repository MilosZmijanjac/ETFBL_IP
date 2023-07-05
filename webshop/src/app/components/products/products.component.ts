import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { CartService } from '../../services/cart.service';
import { Product } from 'src/app/models/domains/Product';
import { SpecialAttribute } from 'src/app/models/domains/SpecialAttribute';
import { Category } from 'src/app/models/domains/Category';


@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  products: Product[] = [];
  productsToShow: Product[] = [];
  loading = false;
  itemsPerPage: number = 11;
  currentPage: number = 1;
  totalPages: number = 1;
  startIndex: number = 0;
  endIndex: number = 9;
  constructor(private productService: ProductService, private cartService: CartService) { }

  ngOnInit(): void {
    this.productService.productList().subscribe((data) => {
      this.products = data;
      this.productsToShow = data;
      this.currentPage = 1;
      if (this.productService.getSearch() !== "") {
        this.productsToShow = this.productsToShow?.filter(p => p.title.includes(this.productService.getSearch()));
        this.productService.setSearch("");
      }
      this.totalPages = Math.ceil(this.productsToShow.length / this.itemsPerPage);
      this.startIndex = (this.currentPage - 1) * this.itemsPerPage;
      this.endIndex = this.startIndex + this.itemsPerPage;
    })
  }
  filter(eventData: { category: Category, filterAttributes: SpecialAttribute[], city: string, country: string, state: string, priceFrom: number, priceTo: number }) {
    console.log(eventData)
    this.productsToShow = this.products;
    if (eventData.category.name !== "All") {
      console.log("cat")
      this.productsToShow = this.productsToShow?.filter(p => p.categoryId.toString() == eventData.category.id);
      console.log(this.productsToShow)
    }
    if (eventData.priceFrom.toString() !== "" && eventData.priceTo.toString() != "") {
      console.log("price")
      this.productsToShow = this.productsToShow?.filter(p => p.price >= eventData.priceFrom && p.price <= eventData.priceTo);
      console.log(this.productsToShow)
    }
    if (eventData.state !== "" && eventData.state !== "ALL") {
      console.log("state")
      this.productsToShow = this.productsToShow?.filter(p => p.state == eventData.state);
      console.log(this.productsToShow)
    }
    if (eventData.city !== "") {
      console.log("city")
      this.productsToShow = this.productsToShow?.filter(p => p.city.toLowerCase() == eventData.city.toLowerCase());
      console.log(this.productsToShow)
    }
    if (eventData.country !== "") {
      console.log("county")
      this.productsToShow = this.productsToShow?.filter(p => p.country.toLowerCase() == eventData.country.toLowerCase());
      console.log(this.productsToShow)
    }
    if (eventData.filterAttributes.length > 0) {
      console.log("attr")
      for (let i = 0; i < eventData.filterAttributes.length; i++) {
        if (eventData.filterAttributes[i].value !== "")
          this.productsToShow = this.productsToShow?.filter(p => p.extendedAttributes[i].value == eventData.filterAttributes[i].value);
      }
      console.log(this.productsToShow)
    }
  }
  addToCart(item: Product) {
    this.cartService.addToCart(item);
  }
  onScroll(): void {
    if (this.currentPage < this.totalPages)
      this.currentPage++;

    console.log("scroll page: " + this.currentPage);
  }
  status: boolean = false;
  clickEvent() {
    this.status = !this.status;
  }
}
