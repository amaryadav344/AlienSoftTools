import {IMessage} from './IMessage';
import {IGroup} from './IGroup';

export interface IVRule {
  label: string;
  value: string;
  expression?: string;
  isObjectRule?: boolean;
  message?: IMessage;
  isInitialLoad?: boolean;
  isHardError?: boolean;
  isUpdateRule?: boolean;
  isDeleteRule?: boolean;
  groups?: IGroup[];
}
