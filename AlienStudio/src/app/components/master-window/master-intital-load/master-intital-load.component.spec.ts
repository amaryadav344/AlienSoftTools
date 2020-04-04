import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {MasterIntitalLoadComponent} from './master-intital-load.component';

describe('MasterIntitalLoadComponent', () => {
  let component: MasterIntitalLoadComponent;
  let fixture: ComponentFixture<MasterIntitalLoadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [MasterIntitalLoadComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MasterIntitalLoadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
