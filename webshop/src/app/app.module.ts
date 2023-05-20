import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { UserAuthComponent } from './components/user-auth/user-auth.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SupportComponent } from './components/support/support.component';
import { ProductsComponent } from './components/products/products.component';
import { CartComponent } from './components/cart/cart.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { InfiniteScrollComponent } from './components/infinite-scroll/infinite-scroll.component';
import { CategoriesComponent } from './components/categories/categories.component';
import { UserService } from './services/user.service';
import { JWT_OPTIONS, JwtModule } from '@auth0/angular-jwt';
import { UserDetailsComponent } from './components/user-details/user-details.component';

export const LOCALSTORAGE_TOKEN_KEYS = 'tokens';
export const LOCALSTORAGE_USER_KEYS = 'user';
export function jwtOptionFactor(authService: UserService) {
  return {
    tokenGetter: () => authService.getAccessToken(),
    allowedDomains: ['localhost:3000', 'localhost:8080','localhost:8081'],
    disallowedRoutes: ['http://localhost:3000/api/login'],
  };
}
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    UserAuthComponent,
    SupportComponent,
    ProductsComponent,
    CartComponent,
    ProductDetailsComponent,
    InfiniteScrollComponent,
    CategoriesComponent,
    UserDetailsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    AppRoutingModule, // Jwt Helper Module Import
    JwtModule.forRoot({
      jwtOptionsProvider: {
        provide: JWT_OPTIONS,
        useFactory: jwtOptionFactor,
        deps:[UserService]
      },
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
