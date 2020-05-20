import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AttributeInfoDialogComponent } from './attribute-info-dialog.component';

describe('AttributeInfoDialogComponent', () => {
  let component: AttributeInfoDialogComponent;
  let fixture: ComponentFixture<AttributeInfoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AttributeInfoDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AttributeInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
