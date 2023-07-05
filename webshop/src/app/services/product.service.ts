import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NewProductRequest } from '../models/requests/NewProductRequest';
import { Product } from '../models/domains/Product';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  searchTerm: string = '';
  constructor(private http: HttpClient) { }
  addProduct(data: NewProductRequest) {
    return this.http.post<Product>('http://localhost:8080/api/products', data);
  }
  productList() {
    return this.http.get<Product[]>('http://localhost:8080/api/products');
  }
  popularProductList() {
    return this.http.get<Product[]>('http://localhost:8080/api/products/home');
  }
  userProductList(username: string) {
    return this.http.get<Product[]>('http://localhost:8080/api/products/user/' + username);
  }

  deleteProduct(id: number) {
    return this.http.delete(`http://localhost:8080/api/products/${id}`);
  }

  getProduct(id: string) {
    return this.http.get<Product>(`http://localhost:8080/api/products/${id}`);
  }

  endProduct(id: number) {
    return this.http.get<Product>(`http://localhost:8080/api/products/deactivate/${id}`);
  }
  activateProduct(id: number) {
    return this.http.get<Product>(`http://localhost:8080/api/products/activate/${id}`);
  }

  setSearch(term: string) {
    this.searchTerm = term;
  }
  getSearch() {
    return this.searchTerm;
  }

}
