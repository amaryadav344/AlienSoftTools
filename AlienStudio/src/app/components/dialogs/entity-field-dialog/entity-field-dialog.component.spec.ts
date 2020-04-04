import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {EntityFieldDialogComponent} from './entity-field-dialog.component';

describe('EntityFieldDialogComponent', () => {
  let component: EntityFieldDialogComponent;
  let fixture: ComponentFixture<EntityFieldDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [EntityFieldDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EntityFieldDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
