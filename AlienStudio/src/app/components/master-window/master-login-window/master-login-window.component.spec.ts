import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MasterLoginWindowComponent} from './master-login-window.component';

describe('MasterLoginWindowComponent', () => {
  let component: MasterLoginWindowComponent;
  let fixture: ComponentFixture<MasterLoginWindowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MasterLoginWindowComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MasterLoginWindowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
