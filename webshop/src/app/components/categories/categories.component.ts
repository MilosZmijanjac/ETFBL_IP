import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormGroup, NgForm } from '@angular/forms';
import { Category } from 'src/app/models/domains/Category';
import { SpecialAttribute } from 'src/app/models/domains/SpecialAttribute';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  @Output() productFilter = new EventEmitter<{ category:Category,filterAttributes:SpecialAttribute[],city:string,country:string,state:string,priceFrom: number, priceTo: number }>();

  categories: Category[] = [{name: "All",specialAttributes:[],id:''}];
  selectedCategory: Category={name: "All",specialAttributes:[],id:''};
  attributes: SpecialAttribute[] = [];
  selectedState: string = "ALL";

  filterForm!: FormGroup
  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(
      data => {
        this.categories.push(...data);
        console.log(data)
      }
    );
  }
  filterProducts(data: NgForm): void {
    let localFilterAttributes:SpecialAttribute[]=[];
    if(this.selectedCategory.name!=="All"){
    this.selectedCategory.specialAttributes.forEach(
      cat=>{
        let temp=cat;
        temp.value=data.controls[cat.name].value;
        localFilterAttributes.push(temp);
      }
    )
    }

    this.productFilter.emit({ category:this.selectedCategory,filterAttributes:localFilterAttributes,city:data.value.city,country:data.value.country,state:this.selectedState,priceFrom: data.value.priceFrom, priceTo: data.value.priceTo })
  }
  getAttributes() {
    if (this.selectedCategory === null)
      return;
    this.attributes = this.selectedCategory.specialAttributes;
  }
}
