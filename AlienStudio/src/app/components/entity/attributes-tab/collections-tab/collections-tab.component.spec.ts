import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CollectionsTabComponent} from './collections-tab.component';

describe('CollectionsTabComponent', () => {
  let component: CollectionsTabComponent;
  let fixture: ComponentFixture<CollectionsTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CollectionsTabComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CollectionsTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
