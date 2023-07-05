import { Injectable } from '@angular/core';
import { Category } from '../models/domains/Category';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient: HttpClient) {}

  getCategories(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(
      'http://localhost:8080/api/category/'
    );
  }

}
