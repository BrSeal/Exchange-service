import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RatesFormWithBaseComponent } from './rates-form-with-base.component';

describe('RatesFormWithBaseComponent', () => {
  let component: RatesFormWithBaseComponent;
  let fixture: ComponentFixture<RatesFormWithBaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RatesFormWithBaseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RatesFormWithBaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
