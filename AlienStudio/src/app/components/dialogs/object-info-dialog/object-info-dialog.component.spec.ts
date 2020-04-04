import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ObjectInfoDialogComponent} from './object-info-dialog.component';

describe('ObjectInfoDialogComponent', () => {
  let component: ObjectInfoDialogComponent;
  let fixture: ComponentFixture<ObjectInfoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ObjectInfoDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ObjectInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
