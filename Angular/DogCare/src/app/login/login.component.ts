import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
 

  email: string ="";
  password: string ="";


  constructor(
    private router: Router,
    private http: HttpClient,
    private userService: UserService) {}
 

  navigateToRegister() {
    this.router.navigateByUrl('/register'); // Change '/register' to match your register route path
  }

  Login() {
    console.log(this.email);
    console.log(this.password);
 
    let bodyData = {
      email: this.email,
      password: this.password,
    };
 
    this.http.post("http://localhost:8080/auth/login", bodyData).subscribe(
      (resultData: any) => {
        console.log(resultData);
        if (resultData.token) {
          localStorage.setItem('token', resultData.token); 
          this.userService.setUserId(resultData.userID);
          this.router.navigateByUrl('/home');
        }
      },
      (error) => {
        console.error(error);
        if (error.status === 401) { // Check for the status code 401
          alert("Email and Password not match");
        } else {
          // Handle other errors
          alert("An error occurred");
        }
      }
    );
    
    }

}