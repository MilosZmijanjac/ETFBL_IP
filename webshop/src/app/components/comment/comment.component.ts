import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActiveComment } from 'src/app/models/domains/ActiveComment';
import { CommentModel } from 'src/app/models/domains/CommentModel';
import { ActiveCommentTypeEnum } from 'src/app/models/enums/ActiveCommentTypeEnum';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  @Input() comment!: CommentModel;
  @Input() activeComment!: ActiveComment | null;
  @Input() replies!: CommentModel[];
  @Input() parentId!: string | null;
  @Input() userLoggedIn!:boolean;

  @Output()
  setActiveComment = new EventEmitter<ActiveComment | null>();
  @Output()
  addComment = new EventEmitter<{ text: string; parentId: string | null }>();

  createdAt: string = '';
  canReply: boolean = true;
  activeCommentType = ActiveCommentTypeEnum;
  replyId: string | null = null;

  ngOnInit(): void {
    const fiveMinutes = 300000;
    const timePassed =
      new Date().getMilliseconds() -
        new Date(this.comment.createdAt).getMilliseconds() >
      fiveMinutes;
    this.createdAt = new Date(this.comment.createdAt).toLocaleString();
    this.replyId = this.parentId ? this.parentId : this.comment.id;
  }

  isReplying(): boolean {
    if (!this.activeComment) {
      return false;
    }
    return (
      this.activeComment.id === this.comment.id &&
      this.activeComment.type === this.activeCommentType.replying
    );
  }

}