import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CollectionInfoDialogComponent } from './collection-info-dialog.component';

describe('CollectionInfoDialogComponent', () => {
  let component: CollectionInfoDialogComponent;
  let fixture: ComponentFixture<CollectionInfoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CollectionInfoDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CollectionInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
