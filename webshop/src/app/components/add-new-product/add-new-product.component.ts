import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Category } from 'src/app/models/domains/Category';
import { SpecialAttribute } from 'src/app/models/domains/SpecialAttribute';
import { NewProductRequest } from 'src/app/models/requests/NewProductRequest';
import { CategoryService } from 'src/app/services/category.service';
import { FileService } from 'src/app/services/file.service';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-add-new-product',
  templateUrl: './add-new-product.component.html',
  styleUrls: ['./add-new-product.component.css']
})
export class AddNewProductComponent implements OnInit {

  categories:Category[]=[];
  selectedCategory!:Category;
  attributes:SpecialAttribute[]=[];
  formData!:FormData;
  selectedState:string="NEW";
  constructor(private categoryService: CategoryService,private router: Router,private productService: ProductService,private fileService:FileService,private userService:UserService ) {}

  
  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(
      data=>{
        this.categories=data
        console.log(data)
      }
    );
  }
  createNewProduct(data:NgForm):void{
    let specialAtrributes:SpecialAttribute[]=[];
    this.attributes.forEach(attribute=>specialAtrributes.push({
      name:attribute.name,
      value:data.controls[attribute.name].value,
      possibleValues:attribute.possibleValues,
      categoryId:attribute.categoryId,
      unit:attribute.unit
    }));

    console.log(specialAtrributes)
    let request:NewProductRequest={
      title:data.value.title,
      price:data.value.price,
      description:data.value.description,
      imagesPath:data.value.imagesPath,
    address:data.value.address,
    city: data.value.city,
    country: data.value.country,
    status:"ACTIVE",
    state:this.selectedState,
    extendedAttributes:specialAtrributes,
    username:this.userService.getUsername(),
    categoryId:this.selectedCategory.id
    };
    console.log(request);
    this.productService.addProduct(request).subscribe(resp=>{
      if(this.formData!==null)
      this.fileService.uploadProduct(this.formData,request.username,resp.id).subscribe();
    })
    this.router.navigate(['loading']).then(() => {
      this.router.navigate(['products']).then(() => {
      
      });
    });
    
  }
  getAttributes(){
    
    if(this.selectedCategory===null)
    return;
   this.attributes=this.selectedCategory.specialAttributes;
  }
  onFileSelected(event:any) {
    const file: File = event.target.files[0];

    if (file) {
      this.formData = new FormData();
      this.formData.append("files", file);
      
    }
}
}
