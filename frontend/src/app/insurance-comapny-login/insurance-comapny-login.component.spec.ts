import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsuranceComapnyLoginComponent } from './insurance-comapny-login.component';

describe('InsuranceComapnyLoginComponent', () => {
  let component: InsuranceComapnyLoginComponent;
  let fixture: ComponentFixture<InsuranceComapnyLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InsuranceComapnyLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InsuranceComapnyLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
