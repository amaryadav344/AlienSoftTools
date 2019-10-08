import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomMethodInfoDialogComponent } from './custom-method-info-dialog.component';

describe('CustomMethodInfoDialogComponent', () => {
  let component: CustomMethodInfoDialogComponent;
  let fixture: ComponentFixture<CustomMethodInfoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CustomMethodInfoDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomMethodInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
