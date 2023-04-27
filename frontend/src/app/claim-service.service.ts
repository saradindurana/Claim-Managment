import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';

interface ClaimResponse 
  {
    insuranceCompanyApproval: boolean,
    amountApprovedBySurveyor: number,
    claimId: String,
    dateOfAccident: Date,
    claimStatus: boolean,
    surveyorFees: number,
    estimatedLoss: number,
    withdrawClaim: boolean
}
interface UpdateClaim
{
  claimId: string,
  dateOfAccident: Date,
  claimStatus: boolean,
  insuranceCompanyApproval: boolean,
  amountApprovedBySurveyor: number,
  withdrawClaim: boolean,
  surveyorFees: number,
  estimatedLoss: number
}
interface addClaim{
  String:any
}

  export interface ClaimDetails{
    claimStatus: boolean,
    insuranceCompanyApproval: boolean,
    amountApprovedBySurveyor: number,
    surveyor:[surveyorId:number]
    }
  interface ClaimStatusResponse {
    claimId: string;
    claimStatus: string;
    claimDescription: string;
  }
  export interface Survey {
    claim_Id:String,
    policy_No:String,
    labour_Charges:number,
    parts_Cost:number,
    policy_Class:number,
    depreciation_Cost:number,
    total_Amount:number
}

  interface Surveyor{
    surveyorId: number,
    firstName: string,
    estimateLimit: number,
    lastName: string
  }
  @Injectable({
    providedIn: 'root',
  })
  export class ClaimsService {
    data:any=[];
    data2:any=[];
    Surveyor:any=[];
    singleClaim:any=[];
    claims:any=[];
    claimReport:any=[];
    paymentReport:any=[];
    claimResponse: ClaimResponse | null = null;
    claimStatusResponse: ClaimStatusResponse | null = null;
    updateClaim:UpdateClaim|null=null;
    surveyor:Surveyor |null=null;
    fee:any=[];
    private headers = new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem('token')}`
    });
    constructor(private http: HttpClient, private router: Router) {
      interface singleClaim{
      
      }
    }
  
    viewUnsurvyed() {
      return this.http
        .get<ClaimResponse>(
          environment.CLAIM_SERVICE_URL+'/unsurvyed',
          {headers:this.headers}
        )
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.data = response;
          })
          );
    }
    viewsurvyed() {
      
      return this.http
        .get<ClaimResponse>(
          environment.CLAIM_SERVICE_URL+'/survyed',
          {headers:this.headers}
        )
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.data2 = response;
            console.log(this.data2)
          })
          );
    }
    getClaimById(claimId: any): Observable<any> {
      return this.http.get( environment.CLAIM_SERVICE_URL+'/'+claimId,{headers:this.headers});
    }
    getAllSurveyor() {
      return this.http
        .get<Surveyor>(
          environment.SURVEYOR_SERVICE_URL,
          {headers:this.headers}
        )
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.Surveyor = response;
          })
        );
    }

    //List of eligible Surveyors

    viewEligibleSurveyors(estimatedLoss:any): Observable<any> {
      return this.http.get( environment.CLAIM_SERVICE_URL+'/surveyors/'+estimatedLoss,
      {headers:this.headers}
      );
    }
    
  
    submitClaim(surveyor:any,claimStatus:any,claimId:any) {
      console.log("''''''''''''''''''''''''''''''''''''''")
      console.log(claimStatus,surveyor,claimId);
      return this.http
        .put<UpdateClaim>(
          environment.CLAIM_SERVICE_URL+'/'+claimId+'/update',{claimStatus,surveyor},
          {headers:this.headers}
                  )
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.data = response;
          })
          );
    }
    releaseFunds(insuranceCompanyApproval:any,claimStatus:any,claimId:any,amountApprovedBySurveyor:any) : Observable<any> {
                    
      alert('inside service')
      return this.http.put(  environment.CLAIM_SERVICE_URL+'/'+claimId+'/releasefund',{insuranceCompanyApproval,claimStatus,amountApprovedBySurveyor},
      {headers:this.headers});
                  }
          
    
    addClaim(policyNo:any,estimatedLoss:any,dateOfAccident:any) {
      const body = { policyNo, estimatedLoss, dateOfAccident };
      return this.http.post(`${environment.Insured_SERVICE_URL}/addClaim`, body, {headers:this.headers})
                  
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.data = response;
          })
          );
    }
    allClaims() {
      return this.http
        .get<addClaim>(
          environment.CLAIM_SERVICE_URL
        )
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.claims = response;
          })
        );
    }
    addSyrveyReport(policy_No:any,claim_Id:any,labour_Charges:any,parts_Cost:any,policy_Class:any,depreciation_Cost:any) {
      return this.http
        .post(
          environment.SURVEY_REPORT,{
            claim_Id,
            policy_No,
            labour_Charges,
            parts_Cost,
            policy_Class,
            depreciation_Cost},{responseType: 'text'}
        )
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.claims = response;
          })
        );
    }

withdrawClaim(claim_Id:any) {
      console.log("''''''''''''''''''''''''''''''''''''''")
      return this.http
        .put(
          environment.Insured_SERVICE_URL+'/update/'+claim_Id,{ClaimStatus:false,withdrawClaim:true},{responseType: 'text'})
                  
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.data = response;
          })
          );
    }
    acceptClaim(claim_Id:any) {
      console.log("''''''''''''''''''''''''''''''''''''''")
      return this.http
        .put(
          environment.Insured_SERVICE_URL+'/update/'+claim_Id,{ClaimStatus:false,withdrawClaim:false},{responseType: 'text'})
                  
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.data = response;
          })
          );
    }
    pendingClaim(month:any,year:any){
      console.log("''''''''''''''''''''''''''''''''''''''")
      console.log(month,year)
      return this.http
        .get(
          environment.IRDA_SERVICE+'claimStatus/pull/'+month+'/'+year,{})                 
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.data = response;
          })
          );
    }
    pendingPayment(month:any,year:any){
      console.log("''''''''''''''''''''''''''''''''''''''")
      console.log(month,year)
      return this.http
        .post(
          environment.IRDA_SERVICE+'paymentStatus/pull/'+month+'/'+year,{})                 
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.data = response;
          })
          );
    }
    showPendingClaim(month:any,year:any){
      console.log("''''''''''''''''''''''''''''''''''''''")
      console.log(month,year)
      return this.http
        .get(
          environment.IRDA_SERVICE+'claimStatus/report/'+month+'/'+year,{})                 
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.claimReport = response;
          })
          );
    }
    showPendingPayment(month:any,year:any){
      console.log("''''''''''''''''''''''''''''''''''''''")
      console.log(month,year)
      return this.http
        .get(
          environment.IRDA_SERVICE+'paymentStatus/report/'+month+'/'+year,{})                 
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.paymentReport = response;
          })
          );
    }
    releaseFees(claim_Id:any){
      console.log("''''''''''''''''''''''''''''''''''''''")
      console.log(claim_Id)
      return this.http
        .get(
          environment.CLAIM_Fee+'/surveyorfees/'+claim_Id,{headers:this.headers}
          )        
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.fee = response;
          })
          );
    }
    releaseFund(claim_Id:any){
      console.log("''''''''''''''''''''''''''''''''''''''")
      console.log(claim_Id)
      return this.http
        .get(
          environment.CLAIM_Fee+'/releasefund/'+claim_Id,{headers:this.headers}
          )        
        .pipe(
          catchError(this.handleError),
          tap((response) => {
            this.fee = response;
          })
          );
    }
        getSurvey(claim_Id:String) :Observable<any> {
      return this.http.get("http://localhost:8082/api/survey/"+claim_Id);
    }

    updateSurvey(claim_Id:String,policy_No:any,labour_Charges:any,parts_Cost:any,policy_Class:any,depreciation_Cost:any) {
      return this.http.put("http://localhost:8082/api/survey/"+claim_Id,{claim_Id,policy_No,labour_Charges,parts_Cost,policy_Class,depreciation_Cost});
    }
  

    // viewStatus(inputFields: { claimID: string }) {
    //   return this.http
    //     .get<ClaimStatusResponse>(
    //       environment.CLAIM_SERVICE_URL +
    //         '/getClaimStatus/' +
    //         inputFields.claimID
    //     )
    //     .pipe(
    //       catchError(this.handleError),
    //       tap((response) => {
    //         this.claimStatusResponse = response;
    //         this.billResponse = null;
    //       })
    //     );
    // }
  
    // submitClaim(inputFields: {
    //   // claimId:string
    //   memberId: string;
    //   policyId: string;
    //   hospitalId: string;
    //   benefitId: string;
    //   remarks: number;
    //   claimAmount: number;
    // }) {
    //   console.log(inputFields);
    //   return this.http
    //     .post<ClaimStatusResponse>(
    //       environment.CLAIM_SERVICE_URL + '/submitClaim',
    //       inputFields
    //     )
    //     .pipe(
    //       catchError(this.handleError),
    //       tap((response) => {
    //         this.claimStatusResponse = response;
    //         this.billResponse = null;
    //       })
    //     );
    // }
  
    handleError(errorResponse: HttpErrorResponse) {
      return throwError(()=>errorResponse.error.message);
    }

    claimId:any
    setClaimId(claimId:any){
      this.claimId=claimId
    }
    getClaimId(){
      return this.claimId
    }
  }