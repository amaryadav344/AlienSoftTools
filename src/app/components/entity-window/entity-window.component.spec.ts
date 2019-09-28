import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EntityWindowComponent } from './entity-window.component';

describe('EntityWindowComponent', () => {
  let component: EntityWindowComponent;
  let fixture: ComponentFixture<EntityWindowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EntityWindowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EntityWindowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
