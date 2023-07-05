import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SupportMessageRequest } from '../models/requests/SupportMessageRequest';

@Injectable({
  providedIn: 'root'
})
export class SupportService {
  private server = 'http://localhost:8080';
  constructor(private http: HttpClient) {}
  send(request : SupportMessageRequest){
    return this.http.post<any>("http://localhost:8080/api/support", request);
  }
}
