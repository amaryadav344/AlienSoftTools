import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NewFrameDialogComponent} from './new-frame-dialog.component';

describe('NewFrameDialogComponent', () => {
  let component: NewFrameDialogComponent;
  let fixture: ComponentFixture<NewFrameDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [NewFrameDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewFrameDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
