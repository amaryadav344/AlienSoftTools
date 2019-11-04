import {IMessage} from './IMessage';

export interface IVRule {
  name: string;
  expression?: string;
  isObjectRule?: boolean;
  message?: IMessage;
}
