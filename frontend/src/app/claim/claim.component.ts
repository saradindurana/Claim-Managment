import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { ClaimsService } from '../claim-service.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { InsuranceHeaderComponent } from '../insurance-header/insurance-header.component';
@Component({
  selector: 'app-claim',
  templateUrl: './claim.component.html',
  styleUrls: ['./claim.component.css']
})
export class ClaimComponent {
  constructor(private claimsService: ClaimsService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
  }
  onSubmit(form: NgForm) {
    console.log("____________________________________")
    console.log( form.value.policyno,form.value.loss,form.value.date_acc);
    this.claimsService
        .addClaim(
          form.value.policyno,form.value.loss,form.value.date_acc
          )
        .subscribe({

       
          next:(response) => {
            let m=response;
            Swal.fire({
              icon: 'success',
              title: 'Claim Registered',
              showConfirmButton: true,
            })
          },
          error:(errorMessage) => {
            if(errorMessage=="MaximumClaimLimitReachedException"){

              Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Only one Claim allowed for one Policy',
                
              })
            }
            else{
              Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Policy does not exists',
                
              })
            }
            console.log("ADSDSDSDSDSDSDSDSDSDSDSD")
            console.log(errorMessage);
          }
        }
        );
    
    form
  }

}
