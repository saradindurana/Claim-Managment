import { Component, OnInit } from '@angular/core';
import { ClaimsService } from '../claim-service.service';
import { Router } from '@angular/router';
import { Survey } from '../claim-service.service';

@Component({
  selector: 'app-view-survey-report',
  templateUrl: './view-survey-report.component.html',
  styleUrls: ['./view-survey-report.component.css']
})
export class ViewSurveyReportComponent implements OnInit{

  claim_Id!: string;
  survey!: Survey;

  data: any;

  tableVisible: boolean = false;

  constructor(private surveyService:ClaimsService, private router:Router) {}

  ngOnInit() {
  }

  getSurvey() {
    this.surveyService.getSurvey(this.claim_Id).subscribe(
      {
        next:(data: Object) => {
      console.log(data);
      this.data = data as Survey;
      this.tableVisible = true;
    },
    error:(error: any) => {
      console.error(error);
      this.data = [];
      this.tableVisible = false;
    }
    });
  
  }

  onUpdate(claim_Id:String) {
    this.router.navigate(["/update/"+claim_Id]); 
  }
}

