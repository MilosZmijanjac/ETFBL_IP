import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({providedIn: 'root'})
export class FileService {
  private server = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  uploadProfile(formData: FormData,username:string): Observable<HttpEvent<string[]>> {
    return this.http.post<any>(`${this.server}/api/files/upload/`+username, formData, {
      reportProgress: true,
      observe: 'events'
    });
  }  
  uploadProduct(formData: FormData,username:string,id:number): Observable<HttpEvent<string[]>> {
    return this.http.post<any>(`${this.server}/api/files/upload/`+username+"/"+id, formData, {
      reportProgress: true,
      observe: 'events'
    });
  }
}