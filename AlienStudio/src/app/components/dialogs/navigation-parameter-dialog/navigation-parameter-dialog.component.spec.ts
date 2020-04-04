import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {NavigationParameterDialogComponent} from './navigation-parameter-dialog.component';

describe('NavigationParameterDialogComponent', () => {
  let component: NavigationParameterDialogComponent;
  let fixture: ComponentFixture<NavigationParameterDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [NavigationParameterDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavigationParameterDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
