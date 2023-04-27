import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClaimComponent } from './claim/claim.component';
import { RouterModule,Routes,Router } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FormsModule } from '@angular/forms';
import { InsuranceComapnyLoginComponent } from './insurance-comapny-login/insurance-comapny-login.component';
import { SurveyorLoginComponent } from './surveyor-login/surveyor-login.component';
import { ViewSurveyReportComponent } from './view-survey-report/view-survey-report.component';
import { UpdateClaimComponent } from './update-claim/update-claim.component';
import { SurveyorComponent } from './surveyor/surveyor.component';
import { UpdateSurveyComponent } from './update-survey/update-survey.component';
import { InsuranceHeaderComponent } from './insurance-header/insurance-header.component';
import { ListClaimsComponent } from './list-claims/list-claims.component';
import { HttpClientModule, HttpHeaders } from '@angular/common/http';
import { UnallocatedComponent } from './unallocated/unallocated.component';
import { FeeComponent } from './fee/fee.component';
import { newsurveyComponent } from './new-survey/new-survey.component';
import { FooterComponent } from './footer/footer.component';


@NgModule({
  declarations: [
    AppComponent,
    ClaimComponent,
    HomeComponent,
    InsuranceComapnyLoginComponent,
    ListClaimsComponent,
    SurveyorLoginComponent,
    newsurveyComponent,
    ViewSurveyReportComponent,
    UpdateClaimComponent,
    SurveyorComponent,
    UpdateSurveyComponent,
    InsuranceHeaderComponent,
   
    UnallocatedComponent,
        FeeComponent,
        FooterComponent,
   
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    
   
   
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
