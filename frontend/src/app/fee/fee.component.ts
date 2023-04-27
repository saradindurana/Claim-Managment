import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2'
import { ClaimsService } from '../claim-service.service';
@Component({
  selector: 'app-fee',
  templateUrl: './fee.component.html',
  styleUrls: ['./fee.component.css']
})
export class FeeComponent implements OnInit {
feeReport:any;
isEnabled: boolean = false;
error:any;
Visible:boolean=false;
  constructor(private claimsService: ClaimsService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
  }
  onSubmit(form:NgForm){
console.log(form.value.claimid);
this.claimsService.releaseFees(form.value.claimid).subscribe({


  next:(response)=>{
    this.feeReport=response;
    console.log(this.feeReport.fees);
    // this.isEnabled=true
    // this.Visible=false;
    Swal.fire({
      icon: 'success',
      title: 'Rupees ' + this.feeReport.fees + ' paid to the surveyor' ,
      showConfirmButton: true,
    })
    this.router.navigate(['listclaims'])
},
error:(errorMessage) => {
console.log(errorMessage);
this.error=errorMessage;
this.Visible=true;
}
}) 
  }


  unallocated(){
    this.router.navigate(['unallocated']);
  }
  allocated(){
    this.router.navigate(['listclaims']);
  }
done(){
  Swal.fire({
    icon: 'success',
    title: 'Fees paid to the surveyor',
    showConfirmButton: true,
  })
}
}
