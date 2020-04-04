import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MasterWindowComponent} from './master-window.component';

describe('MasterWindowComponent', () => {
  let component: MasterWindowComponent;
  let fixture: ComponentFixture<MasterWindowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MasterWindowComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MasterWindowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
