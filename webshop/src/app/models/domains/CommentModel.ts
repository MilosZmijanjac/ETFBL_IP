export interface CommentModel {
    id: string;
    body: string;
    username: string;
    userId: string;
    parentId: null | string;
    createdAt: string;
  }