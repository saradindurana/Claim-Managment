import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClaimComponent } from './claim/claim.component';
import { HomeComponent } from './home/home.component';
import { InsuranceComapnyLoginComponent } from './insurance-comapny-login/insurance-comapny-login.component';
import { ListClaimsComponent } from './list-claims/list-claims.component';
import { SurveyorLoginComponent } from './surveyor-login/surveyor-login.component';
import { ViewSurveyReportComponent } from './view-survey-report/view-survey-report.component';
import { UpdateClaimComponent } from './update-claim/update-claim.component';
import { UnallocatedComponent } from './unallocated/unallocated.component';
import { FeeComponent } from './fee/fee.component';
import { newsurveyComponent } from './new-survey/new-survey.component';
import { UpdateSurveyComponent } from './update-survey/update-survey.component';
import { AuthGuard } from './auth/auth.guard';


const routes: Routes = [
 
  {
    path:"",
    component: HomeComponent
  },
  {
    path:"insuranceCompanyLogin",
    component:InsuranceComapnyLoginComponent
  },
  {
    path:"listclaims",
    component:ListClaimsComponent,
    canActivate:[AuthGuard]
  },
  {
    path:"surveyorLogin",
    component:SurveyorLoginComponent
  },
  {
    path:"newSurveyComponent",
    component:newsurveyComponent
  },
  {
    path:"view",
    component:UpdateSurveyComponent
  },
 
  {
    path:"addclaim",
    component:ClaimComponent
  },
  {
    path:"unallocated",
    component:UnallocatedComponent,
    canActivate:[AuthGuard]
  },
  {
    path:"update",
    component:UpdateClaimComponent,
    canActivate:[AuthGuard]
  },
  {
    path:"fee",
    component:FeeComponent,
    canActivate:[AuthGuard]
  },
  {
    path:"survey",
    component:UpdateSurveyComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
