import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadParamterInfoDialogComponent } from './load-paramter-info-dialog.component';

describe('LoadParamterInfoDialogComponent', () => {
  let component: LoadParamterInfoDialogComponent;
  let fixture: ComponentFixture<LoadParamterInfoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoadParamterInfoDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoadParamterInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
