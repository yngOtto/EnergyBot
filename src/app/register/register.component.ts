import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form: any = {
    user: {
      email: null,
      password: null
    },
    token :null

  };


  isSuccessful = false;
  isSignUpFailed = false;
  errMessage = '';
  isTokenRegistered = false;

  constructor(private authService: AuthService, private router: Router) { }


  ngOnInit(): void {
  }


  redirectToLogin() {
    this.router.navigate(['/login']);
  }



  onSubmit(): void {
    const { email, password } = this.form.user;
    this.authService.register(email, password).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
        this.isTokenRegistered = false;
      },
      err => {
        this.errMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

  onTokenSubmit(): void {
    this.authService.confirm(this.form.token).subscribe(

      data => {
        this.isTokenRegistered = true;

      },
      error => {
        console.error(error);
        this.errMessage = 'An error occurred while registering the token';
      }
    );
  }


}
