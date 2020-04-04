import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RuleInfoDialogComponent} from './rule-info-dialog.component';

describe('RuleInfoDialogComponent', () => {
  let component: RuleInfoDialogComponent;
  let fixture: ComponentFixture<RuleInfoDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RuleInfoDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RuleInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
