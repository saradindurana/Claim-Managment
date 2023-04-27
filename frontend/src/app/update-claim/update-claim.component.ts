import { Component, OnInit } from '@angular/core';
import { ClaimsService } from '../claim-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-claim',
  templateUrl: './update-claim.component.html',
  styleUrls: ['./update-claim.component.css']
})
export class UpdateClaimComponent implements OnInit {
  error: string | null = null;
  isValidating: boolean = false;
  singleClaim: any = [];
  surveyor: any = [];
  claimId: any;
  insuranceCompanyApproval: any;
  cid: any
  constructor(private claimsService: ClaimsService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.singleClaim = this.claimsService.updateClaim;
    this.claimId = this.claimsService.getClaimId();
    console.log("this id from update" + this.claimId)

    //for getting details by id
    this.claimsService.getClaimById(this.claimId).subscribe({


      next: (response) => {
        this.singleClaim = response
        console.log("this is from update" + this.singleClaim.claimId)
        console.log(response);


        //for eligible surveyor
  
        this.claimsService.viewEligibleSurveyors(this.singleClaim.estimatedLoss).subscribe({


          next: (response) => {
            console.log(response);
            this.surveyor = (response);
            console.log(this.surveyor);
          },
          error: (errorMessage) => {
            console.log(errorMessage);
          }
        })
      },
      error: (errorMessage) => {
        console.log(errorMessage);
      }
    });

    
    
    
  }

  onSubmit(myform: any) {
    const surveyor={
      claimDetails:myform.value.surve.claimDetails,
      estimateLimit:myform.value.surve.estimateLimit,
      firstName:myform.value.surve.firstName,
      lastName:myform.value.surve.lastName,
      surveyorId:myform.value.surve.surveyorId
        }
      
    
    console.log("____________________________________")
    console.log(surveyor)
    this.claimsService
        .submitClaim(
          surveyor,myform.value.claimStatus,this.claimId)
        .subscribe({

        
        next:  (response) => {
            Swal.fire({
              icon: 'success',
              title: 'Surveyor allocated',
              showConfirmButton: true,
            })
            this.router.navigate(['listclaims']);
          },
         error: (errorMessage) => {
            this.isValidating = false;
            this.error = 'Invalid Details. Please check your details again';
            console.log(errorMessage);
          }
  });
    
  }
releasefund(){
  this.claimsService
        .releaseFund(this.claimId)
        .subscribe({

        
        next:  (response) => {
            Swal.fire({
              icon: 'success',
              title: 'Funds released for claim: ' + this.claimId,
              showConfirmButton: true,
            })
            this.router.navigate(['listclaims'])
          },
         error: (errorMessage) => {
            this.isValidating = false;
            this.error = 'Invalid Details. Please check your details again';
            console.log(errorMessage);
          }
  });
    

}
}
