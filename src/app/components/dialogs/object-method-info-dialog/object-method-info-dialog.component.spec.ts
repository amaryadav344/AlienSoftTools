import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ObjectMethodInfoDialogComponent } from './object-method-info-dialog.component';

describe('ObjectMethodInfoDialogComponent', () => {
  let component: ObjectMethodInfoDialogComponent;
  let fixture: ComponentFixture<ObjectMethodInfoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ObjectMethodInfoDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ObjectMethodInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
