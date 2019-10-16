import {TestBed} from '@angular/core/testing';

import {TabChangeServiceService} from './tab-change-service.service';

describe('TabChangeServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TabChangeServiceService = TestBed.get(TabChangeServiceService);
    expect(service).toBeTruthy();
  });
});
