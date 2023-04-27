import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-surveyor-login',
  templateUrl: './surveyor-login.component.html',
  styleUrls: ['./surveyor-login.component.css']
})
export class SurveyorLoginComponent {
  constructor(private router: Router) {}

error=false;

  login(data:any){
    if(data.user == "survey" && data.password=="survey") {
      this.error=false;
      this.router.navigate(['/newSurveyComponent']);

    }
    else{
      this.error=true;
    }
    
  }

}
