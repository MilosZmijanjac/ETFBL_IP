import { Component, Input, OnInit, Output } from '@angular/core';
import { ActiveComment } from 'src/app/models/domains/ActiveComment';
import { CommentModel } from 'src/app/models/domains/CommentModel';
import { CommentsService } from 'src/app/services/comments.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {
  @Input() productId!: number;
  comments: CommentModel[] = [];
  isUserlogged:boolean=false;
  activeComment: ActiveComment | null = null;

  constructor(private commentsService: CommentsService,private userService:UserService) {}

  ngOnInit(): void {
    this.commentsService.getComments(this.productId).subscribe((comments) => {
      this.comments = comments;
    });
    this.isUserlogged=this.userService.isLoggedIn();
  }

  getRootComments(): CommentModel[] {
    return this.comments.filter((comment) => comment.parentId === null);
  }
  setActiveComment(activeComment: ActiveComment | null): void {
    this.activeComment = activeComment;
  }

  addComment({
    text,
    parentId,
  }: {
    text: string;
    parentId: string | null;
  }): void {
    this.commentsService
      .createComment({body:text,parentId:parentId,username:this.userService.getUsername(),productId:this.productId.toString()})
      .subscribe((createdComment) => {
        this.comments = [...this.comments, createdComment];
        this.activeComment = null;
      });
  }

  getReplies(commentId: string): CommentModel[] {
    return this.comments
      .filter((comment) => comment.parentId === commentId)
      .sort(
        (a, b) =>
          new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime()
      );
  }
}
