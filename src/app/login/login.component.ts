import { Component, OnInit } from '@angular/core';
import { faUser, faUserLock } from '@fortawesome/free-solid-svg-icons';
import { faLock } from '@fortawesome/free-solid-svg-icons';
import { NgModel } from '@angular/forms';
import { User } from '../user';
import { AuthService } from '../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user:User = new User();

  form: any = {
    email: null,
    password: null
  };

  isSuccessful = false;
  loginFailed = false;
  errMessage = '';

  faUser = faUser;
  faLock = faLock;

  constructor(private authService: AuthService, private router: Router) { }


  ngOnInit(): void {
  }

  onSubmit(): void {
    const { email, password } = this.form;
    this.authService.login(email, password).subscribe(
      data => {
        if (data.success) {
          this.user.email= email
          this.user.password = password
          this.user.token = data.token
          this.isSuccessful = true;
          this.loginFailed = false;
          this.router.navigate(['/dashboard']);
        }
      },
      err => {
        this.errMessage = err.error.message;
        this.loginFailed = true;
      }
    );
  }

  userLogin() {
    console.log(this.user);
  }

}
