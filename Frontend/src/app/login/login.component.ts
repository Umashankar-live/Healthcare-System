import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router,ActivatedRoute } from '@angular/router';
import { Login } from 'src/models/login.model';
import { User } from 'src/models/user.model';
import { LoginserviceService } from 'src/service/loginservice.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userName: string = null
  password: string = null
  userType: string = null

  loginSucc: boolean = true
  isLogginIn: boolean = false
  isInvalidAttempt: boolean = false
  inputType: string = "password"
  errMsg: string = "Please check your inputs.."
  
  constructor(private router: Router, private loginservice: LoginserviceService,private route: ActivatedRoute) { 
    
  }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(
      args => {
        if(args.get('redirect'))
          this.isInvalidAttempt = true
      }
    )
  }

    //For view password
    toggle() {
      if (this.inputType == "password")
        this.inputType = "text"
      else
        this.inputType = "password"
    }

  // checkRole(user: UserModel) {

  //   if (user == null) {
  //     alert("Invalid Username and Password");
  //   }
  //   sessionStorage.setItem('user', this.user.userId);
  //   console.log(user.role);

  //   if (user.role == "admin") {

  //     this.route.navigate(['admin']);

  //   }else if (user.role == "user") {
      
  //     this.route.navigate(['user']);
     
  //   } else {
  //     alert("You are not registered!")
  //   }
  // }


  // checkLogin() {
  //   console.log(this.login);
  //   this.loginservice.getUser(this.login).subscribe(data => {
  //     this.user = data;
  //     this.checkRole(this.user);
  //   });
  // }

  // loginUser(){
  //   this._service.loginUser(this.user).subscribe(
  //   data=>{
  //     console.log("response received");
  //     this.userr=data;
  //     sessionStorage.setItem("loggedInUser",JSON.stringify(this.userr));
  //     if( this.auth.authenticate(this.userr)){
  //       if(this.userr.role=="Admin")
  //       this.router.navigate(['/admin'])
  //       if(this.userr.role=="Customer")
  //       this.router.navigate(['/customer'])
  //   }
  //     else
  //     this.router.navigate(['/login'])
  //   },
  //   error=>{
  //     console.log("exception occured")
  //     this.msg="Bad Credentials! Please enter valid username/Password";
  //   })
  // }


  onSubmit(form: NgForm) {
    this.isLogginIn = true
    if (form.valid) {
      let login = new Login();

      login.name = this.userName
      login.password = this.password
      this.loginservice.loginUser(login).subscribe(
        res => {
          if (res == null)
            this.loginSucc = false
          this.isLogginIn = false
          if (res.role == "admin") {
            localStorage.setItem('userType', window.btoa("admin"))
            this.router.navigate(['admin/dashboard'])
          }
          else if (res.role == "user") {
            localStorage.setItem('userName', window.btoa(res.userName))
            localStorage.setItem('userType', window.btoa("user"))
            this.router.navigate(['user'])
          }
        }
      )
    }
  }

}
