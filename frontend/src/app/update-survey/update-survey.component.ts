import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';

import Swal from 'sweetalert2';
import { ClaimsService,Survey } from '../claim-service.service';
import { NgForm } from '@angular/forms';
import { NgModule } from '@angular/core';

@Component({
  selector: 'app-update-survey',
  templateUrl: './update-survey.component.html',
  styleUrls: ['./update-survey.component.css']
})
export class UpdateSurveyComponent implements OnInit{
  constructor(private claimsService: ClaimsService,
    private router: Router,
    private route: ActivatedRoute) {
      
     }

  ngOnInit(): void {
  }
  onSubmit(form: NgForm){
    console.log("____________________________________")
    console.log( form.value.policyno,form.value.claimid,form.value.labour_cost,form.value.parts_Cost,form.value.policy_Clause,form.value.dep_cost);
    this.claimsService
        .updateSurvey(
          form.value.claimid,form.value.policyno,form.value.labour_cost,form.value.parts_Cost,form.value.policy_Clause,form.value.dep_cost)
        .subscribe({

        
        next:  (response) => {
            let m=response;
            let total = form.value.labour_cost + form.value.parts_Cost - form.value.policy_Clause - form.value.dep_cost;
            Swal.fire({
              icon: 'success',
              title: 'Amount updated with rupees ' + total +' for claim '+ form.value.claimid,
              showConfirmButton: true,
            })
            this.router.navigate(['newSurveyComponent']);
          },
          error: (errorMessage) => {
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Please Check Your Details and try again',
              
            })
            console.log(errorMessage);
          }
  });
  // alert(form.value.dep_cost +" "+ form.value.policy_Clause+" " + form.value.parts_Cost+ " "+ form.value.labour_cost)
}
policy: any ;
dep_cost: any;
labour_cost:any;
parts_Cost: any
policy_Clause:any 


onLeave(claimid:any){


  this.claimsService.getSurvey(claimid).subscribe({

  
    next:(resp)=>{
      // const policy: Policy = resp;
      // Swal.fire({
      //   icon: 'success',
      //   title:'policy: '+ resp.policy.policyNo+'Fetched',
      //   showConfirmButton: true,
      // })
      // this.policy=resp.policy_No;
      console.log(resp)
      this.policy=resp.policy_No
      this.labour_cost=resp.labour_Charges
      this.policy_Clause=resp.policy_Class
      this.parts_Cost=resp.parts_Cost
      this.dep_cost=resp.depreciation_Cost
      
      
      // console.log(policy.policy_no);
      
    },
    error: (errorMessage)=>{
      console.log(errorMessage)
    }
    
    

  })
}
newsurvey(){
  this.router.navigate(['newSurveyComponent'])
}
  }



