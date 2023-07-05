export interface CommentRequest{
    body:string,
    username:string,
    parentId:string|null,
    productId:string
}