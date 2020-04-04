import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {BusinessObjectTabComponent} from './business-object-tab.component';

describe('BusinessObjectTabComponent', () => {
  let component: BusinessObjectTabComponent;
  let fixture: ComponentFixture<BusinessObjectTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [BusinessObjectTabComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BusinessObjectTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
