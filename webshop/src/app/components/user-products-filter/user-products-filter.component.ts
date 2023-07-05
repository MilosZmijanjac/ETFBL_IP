import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user-products-filter',
  templateUrl: './user-products-filter.component.html',
  styleUrls: ['./user-products-filter.component.css']
})
export class UserProductsFilterComponent implements OnInit {

  @Output() productFilter = new EventEmitter<{ status:string}>();

  options = [
    { label: 'All products', value:"ALL" },
    { label: 'Active products', value:"ACTIVE" },
    { label: 'Inactive products', value:"INACTIVE" }
  ];
  selectedStatus:string="ALL";
  constructor() { }

  ngOnInit(): void {
  }
  filterProducts(data: NgForm): void {
     this.productFilter.emit({ status:this.selectedStatus})
  }
  openCategories(data:NgForm):void{
    console.log(data.value.message);
    
  }
}
