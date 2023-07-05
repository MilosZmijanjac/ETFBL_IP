import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentModel } from '../models/domains/CommentModel';
import { CommentRequest } from '../models/requests/CommentRequest';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private httpClient: HttpClient) {}

  getComments(productId:number): Observable<CommentModel[]> {
    return this.httpClient.get<CommentModel[]>(
      'http://localhost:8080/api/comment/'+productId
    );
  }

  createComment(
   request:CommentRequest
  ): Observable<CommentModel> {
    return this.httpClient.post<CommentModel>(
      'http://localhost:8080/api/comment/',
     request
    );
  }
}