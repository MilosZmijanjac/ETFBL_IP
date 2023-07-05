import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { UserAuthComponent } from './components/user-auth/user-auth.component';
import { ProductsComponent } from './components/products/products.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { CartComponent } from './components/cart/cart.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { OrdersComponent } from './components/orders/orders.component';
import { UserProductsComponent } from './components/user-products/user-products.component';
import { CommentsComponent } from './components/comments/comments.component';
import { LoadingComponent } from './components/loading/loading.component';

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
},{
  component:OrdersComponent,
  path:'orders'
},{
  component:UserProductsComponent,
  path:'user-products'
},{
  component:CommentsComponent,
  path:'details/:productId/comments'
},{
  component:LoadingComponent,
  path:'loading'
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
