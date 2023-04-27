import { Component, OnInit } from '@angular/core';
import { ClaimsService } from '../claim-service.service';
import { ActivatedRoute, Router } from '@angular/router';
@Component({
  selector: 'app-insurance-company',
  templateUrl: './list-claims.component.html',
  styleUrls: ['./list-claims.component.css']
})
export class ListClaimsComponent implements OnInit {
  data:any=[];
  insuranceCompanyApproval: any;
  amountApprovedBySurveyor: any;
  claimId: any;
  dateOfAccident:any;
  claimStatus: any;
  surveyorFees: any;
  estimatedLoss: any;
  withdrawClaim: any;

  constructor(private claimsService: ClaimsService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.claimsService.viewsurvyed().subscribe({

    
     next: (response)=>{
        console.log(response);
        this.data = (response);
        console.log(this.data);
      },
      error:(errorMessage) => {
        console.log(errorMessage);
      }
    }
    )
  }
  gotoUpdate(claimID: any){
    console.log(claimID);
    this.claimsService.setClaimId(claimID);
    this.router.navigate(['update']);
  }
  open(){
    this.router.navigate(['/unallocated']);
  }
gotofee(){
  this.router.navigate(['fee']);
}
}
