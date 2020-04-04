import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {UIToolbarComponent} from './uitoolbar.component';

describe('UIToolbarComponent', () => {
  let component: UIToolbarComponent;
  let fixture: ComponentFixture<UIToolbarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [UIToolbarComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UIToolbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
