import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PropertiesTabComponent} from './properties-tab.component';

describe('PropertiesTabComponent', () => {
  let component: PropertiesTabComponent;
  let fixture: ComponentFixture<PropertiesTabComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PropertiesTabComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PropertiesTabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
