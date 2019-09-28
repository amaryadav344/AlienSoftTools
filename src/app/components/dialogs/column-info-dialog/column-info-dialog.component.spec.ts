import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ColumnInfoDialogComponent } from './column-info-dialog.component';

describe('ColumnInfoDialogComponent', () => {
  let component: ColumnInfoDialogComponent;
  let fixture: ComponentFixture<ColumnInfoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ColumnInfoDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ColumnInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
