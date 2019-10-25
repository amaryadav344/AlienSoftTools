export class WindowStore {
  private static instance: WindowStore;

  private constructor() {
  }

  static getInstance() {
    if (!WindowStore.instance) {
      WindowStore.instance = new WindowStore();
    }
    return WindowStore.instance;
  }
}
