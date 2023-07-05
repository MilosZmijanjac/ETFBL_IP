import { ActiveCommentTypeEnum } from "../enums/ActiveCommentTypeEnum";

export interface ActiveComment {
    id: string;
    type: ActiveCommentTypeEnum;
  }