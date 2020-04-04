import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PropertyInfoDialogComponent} from './property-info-dialog.component';

describe('PropertyInfoDialogComponent', () => {
  let component: PropertyInfoDialogComponent;
  let fixture: ComponentFixture<PropertyInfoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PropertyInfoDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropertyInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
