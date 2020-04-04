import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {QueryTabComponent} from './query-tab.component';

describe('QueryTabComponent', () => {
  let component: QueryTabComponent;
  let fixture: ComponentFixture<QueryTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [QueryTabComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QueryTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
