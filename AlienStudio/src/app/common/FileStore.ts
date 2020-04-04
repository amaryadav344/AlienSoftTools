import {IFile} from '../models/Enitity/IFile';

export class FileStore {
  private static mFileStore: FileStore;
  private files: IFile[];

  constructor() {

  }

  public static getInstance() {
    if (this.mFileStore == null) {
      FileStore.mFileStore = new FileStore();
    }
    return this.mFileStore;
  }

  setFiles(files: IFile[]) {
    this.files = files;
  }

  getFiles() {
    return this.files;
  }

  addFile(file: IFile) {
    this.files.push(file);
    this.files = [...this.files];
  }

  getEntityFiles() {
    return this.getFiles().filter(x => x.name.includes('.ent.xml'));
  }

  getFormFiles() {
    return this.getFiles().filter(x => x.name.includes('.form.xml'));
  }

}

