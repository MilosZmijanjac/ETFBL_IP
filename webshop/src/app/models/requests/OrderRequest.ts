import { OrderItem } from "../domains/OrderItem";

export interface OrderRequest{
    items:OrderItem[];
    username:string;
    totalPrice:number;
    paymentMethod:string;
}