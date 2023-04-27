import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth/auth.service';
@Component({
  selector: 'app-insurance-comapny-login',
  templateUrl: './insurance-comapny-login.component.html',
  styleUrls: ['./insurance-comapny-login.component.css']
})
export class InsuranceComapnyLoginComponent {
  username:string="";
  password:string="";
  invalid:boolean=false;
  
 
  constructor(private auth:AuthService,private router:Router) { }

  ngOnInit(): void {
  }

  login(formdata:any):void
  {
      console.log(formdata.user, formdata.password)
      this.auth.login(formdata.user,formdata.password);
      
    
  }

}
