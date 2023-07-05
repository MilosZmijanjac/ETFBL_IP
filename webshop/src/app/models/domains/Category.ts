import { SpecialAttribute } from "./SpecialAttribute";

export interface Category {
    id: string;
    name: string;
    specialAttributes:SpecialAttribute[];
  }