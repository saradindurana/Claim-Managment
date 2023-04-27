import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClaimsService } from '../claim-service.service';

@Component({
  selector: 'app-unallocated',
  templateUrl: './unallocated.component.html',
  styleUrls: ['./unallocated.component.css']
})
export class UnallocatedComponent {
  data:any=[];
  constructor(private claimsService: ClaimsService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.claimsService.viewUnsurvyed().subscribe({

    
      next:(response)=>{
        console.log(response);
        this.data = (response);
        console.log(this.data);
      },
      error:(errorMessage) => {
        console.log(errorMessage);
      }
    })
  }
  gotoUpdate(claimID: any){
    console.log(claimID);
    this.claimsService.setClaimId(claimID);
    this.router.navigate(['update']);
  }
  openallocated(){
    this.router.navigate(['/listclaims']);
  }
  openpage(){
    this.router.navigate(['fee']);
  }
}
