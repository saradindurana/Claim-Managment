import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ClaimsService } from '../claim-service.service';
import { NgModel } from '@angular/forms';
import Swal from 'sweetalert2'
@Component({
  selector: 'app-surveyor',
  templateUrl: './new-survey.component.html',
  styleUrls: ['./new-survey.component.css']
})
export class newsurveyComponent implements OnInit {

  constructor(private claimsService: ClaimsService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
  }
  onSubmit(form: NgForm){
    console.log("____________________________________")
    console.log( form.value.policyno,form.value.claimid,form.value.labour,form.value.parts,form.value.clause,form.value.dep);
    this.claimsService
        .addSyrveyReport(
          form.value.policyno,form.value.claimid,form.value.labour,form.value.parts,form.value.clause,form.value.dep
          )
        .subscribe({

        
        next:  (response) => {
            let m=response;
            let total = form.value.labour+form.value.parts-form.value.clause-form.value.dep;
            Swal.fire({
              icon: 'success',
              title: total+' rupees Allocated for the claim ' + form.value.claimid,
              showConfirmButton: true,
            })
            this.router.navigate(['survey']);
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
}
policy: any ;
onLeave(claimid:any){

  this.claimsService.getClaimById(claimid).subscribe({

  
    next:(resp)=>{
      Swal.fire({
        icon: 'success',
        title:'policy: '+ resp.policy.policyNo+'Fetched',
        showConfirmButton: true,
      })
      this.policy=resp.policy.policyNo
    },
    error: (errorMessage)=>{
      console.log(errorMessage)
    }
    
    

  })
}
gotoviewsurvey(){
  this.router.navigate(['view'])
}
  
}
