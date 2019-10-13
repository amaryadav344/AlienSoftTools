import {IMessage} from './IMessage';
import {IGroup} from './IGroup';

export interface IVRule {
  name: string;
  expression?: string;
  isObjectRule?: boolean;
  message?: IMessage;
  isInitialLoad?: boolean;
  isHardError?: boolean;
  isUpdateRule?: boolean;
  isDeleteRule?: boolean;
  groups?: IGroup[];
}
