import { SpecialAttribute } from "./SpecialAttribute";

export interface Product{
    id:number;
    title:string;
    description:string;
    price:number;
    imagesPath:string;
    creationTimestamp:string;
    address: string;
    city: string;
    country: string;
    status:string;
    state:string;
    extendedAttributes:SpecialAttribute[];
    userId:number;
    categoryId:number;
}