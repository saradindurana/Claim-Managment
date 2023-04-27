import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SurveyorLoginComponent } from './surveyor-login.component';

describe('SurveyorLoginComponent', () => {
  let component: SurveyorLoginComponent;
  let fixture: ComponentFixture<SurveyorLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SurveyorLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SurveyorLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
