import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewSurveyReportComponent } from './view-survey-report.component';

describe('ViewSurveyReportComponent', () => {
  let component: ViewSurveyReportComponent;
  let fixture: ComponentFixture<ViewSurveyReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewSurveyReportComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewSurveyReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
