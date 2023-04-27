import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ClaimsService } from '../claim-service.service';
import Swal from 'sweetalert2'
@Component({
  selector: 'app-surveyor',
  templateUrl: './surveyor.component.html',
  styleUrls: ['./surveyor.component.css']
})
export class SurveyorComponent implements OnInit {

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
        .subscribe(
          {

          
          next:(response) => {
            let m=response;
            Swal.fire({
              icon: 'success',
              title: m+'\nAmount Allocated',
              showConfirmButton: true,
            })
          },
          error:(errorMessage) => {
            Swal.fire({
              icon: 'error',
              title: 'Oops...',
              text: 'Please Check Your Details and try again',
              
            })
            console.log(errorMessage);
          }
        }
        );
}
}
