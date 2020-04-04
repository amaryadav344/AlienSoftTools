import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {FormInfoDialogComponent} from './form-info-dialog.component';

describe('FormInfoDialogComponent', () => {
  let component: FormInfoDialogComponent;
  let fixture: ComponentFixture<FormInfoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [FormInfoDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
