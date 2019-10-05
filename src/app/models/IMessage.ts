import {IParameter} from './IParameter';
import {IMessageParameter} from './IMessageParameter';

export interface IMessage {
  message: string;
  messageId: number;
  parameters?: IMessageParameter[];
}
