import { SpecialAttribute } from "../domains/SpecialAttribute";

export interface NewProductRequest{
    title:string;
    description:string;
    price:number;
    imagesPath:string;
    address: string;
    city: string;
    country: string;
    status:string;
    state:string;
    extendedAttributes:SpecialAttribute[];
    username:string;
    categoryId:string;
}