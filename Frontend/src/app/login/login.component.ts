import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Login } from 'src/models/login.model';
import { User } from 'src/models/user.model';
import { LoginserviceService } from 'src/service/loginservice.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  user: User;
  userr: User;
  msg = '';

  //custId : string = null
  userName: string = null
  password: string = null
  userType: string = null
  

  loginSucc: boolean = true
  isLogginIn: boolean = false
  isInvalidAttempt: boolean = false
  inputType: string = "password"
  regType: string = "password"
  errMsg: string = "Please enter correct credential.."

  constructor(private router: Router, private loginservice: LoginserviceService, private route: ActivatedRoute) {
    this.user = new User();
    this.userr = new User();
    this.user.gender = null;

  }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(
      args => {
        if (args.get('redirect'))
          this.isInvalidAttempt = true
      }
    )
  }


  registerUser() {
    console.log(this.user)
    this.loginservice.registerUser(this.user).subscribe(
      data => {
        console.log("response received");
        alert('User Registration Successful! Please Login.')
        console.log(data);

      },
      error => {
        console.log("exception occured")
        this.msg = "User with same username already exist";
      })
  }




  //For view password
  toggle() {
    if (this.inputType == "password")
      this.inputType = "text"
    else
      this.inputType = "password"
  }


  //For view password
  regToggle() {
    if (this.regType == "password")
      this.regType = "text"
    else
      this.regType = "password"
  }


  onSubmit(form: NgForm) {
    this.isLogginIn = true
    if (form.valid) {
      let login = new Login();

      login.userName = this.userName
      login.password = this.password
      this.loginservice.loginUser(login).subscribe(
        res => {
          this.userr = res;
          console.log(res.role)
          sessionStorage.setItem('custId',this.userr.userId)
          if (res == null) {
            this.loginSucc = false
            this.isLogginIn = false
          }
          if (res.role == "admin") {
            sessionStorage.setItem('userType', window.btoa("admin"))
            this.router.navigate(['admin/dashboard'])

          }
          else if (res.role == "user") {
            sessionStorage.setItem('uName', window.btoa(res.userName))
            sessionStorage.setItem('userType', window.btoa("user"))
            this.router.navigate(['user/dashboard'])
          }
        }
      )
    }
  }

}
