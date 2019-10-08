import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ObjectsTabComponent } from './objects-tab.component';

describe('ObjectsTabComponent', () => {
  let component: ObjectsTabComponent;
  let fixture: ComponentFixture<ObjectsTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ObjectsTabComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ObjectsTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
