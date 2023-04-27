import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  loggedIn:boolean=false;
  
  constructor(private http: HttpClient,private router:Router) { 
    
  }

  //login method.
  login(username: string, password: string) {
    const body = { username, password };
    console.log(body)
    return this.http.post<any>('http://localhost:8081/api/authenticate', body)
      .subscribe({
        next: (response) => {
       
        const token = response.jwt; 
       
        localStorage.setItem("token",token)
        sessionStorage.setItem("status","valid")
        console.log(localStorage.getItem("token"))
        this.router.navigate(['listclaims']);
      },
      error:(errorMessage) => {
        console.log(errorMessage)
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: 'Please Check Your Details and try again',
          
        })
      }
    }

      )
  }
  
  //logout method.
  logout():void
  {
      // this.loggedIn=false;
      sessionStorage.setItem("status","not");
  }

  //isLoggedIn method.
  isLoggedIn():boolean
  {
    if(sessionStorage.getItem("status")=="valid")
    return true;
    else
    return false;
  }
}
