import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UserAuthComponent } from './components/user-auth/user-auth.component';
import { ProductsComponent } from './components/products/products.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { CartComponent } from './components/cart/cart.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';

const routes: Routes = [{
  component:HomeComponent,
  path:''
},{
  component:UserAuthComponent,
  path:'user-auth'
},{
  component:ProductsComponent,
  path:'products'
},{
  component:ProductDetailsComponent,
  path:'details/:productId'
},{
  component:CartComponent,
  path:'cart'
},{
  component:UserDetailsComponent,
  path:'profile'
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
