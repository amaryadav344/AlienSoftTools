import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ColumnsTabComponent} from './columns-tab.component';

describe('ColumnsTabComponent', () => {
  let component: ColumnsTabComponent;
  let fixture: ComponentFixture<ColumnsTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ColumnsTabComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ColumnsTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
