import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  name: string ="";
  email: string ="";
  password: string ="";


  constructor(
    private http: HttpClient,
    private router: Router // Inject the Router
  ) {}

  save(form: NgForm) {
    if (form.valid) {
      let bodyData = {
        "name": this.name,
        "email": this.email,
        "password": this.password
      };
      this.http.post("http://localhost:8080/users", bodyData, { responseType: 'text' }).subscribe(
        (resultData: any) => {
          console.log(resultData);
          alert("Our Sweet Dog Owner Registered Successfully");
          this.router.navigateByUrl(''); // Redirect to login page
        },
        (error) => {
          console.error(error);
          alert("An error occurred during registration.");
        }
      );
    } else {
      alert("Please fill in all the fields.");
    }
  }
}