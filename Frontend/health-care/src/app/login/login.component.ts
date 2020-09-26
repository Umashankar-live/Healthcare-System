import { Component, OnInit } from '@angular/core';
import { NgForm } from "@angular/forms";
import { Router } from '@angular/router';
import { User } from '../models/user.model';
import { Route } from '@angular/compiler/src/core';
import { LoginserviceService } from '../service/loginservice.service';
import { AuthenticateService } from '../service/authenticate.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  user=new User();
    userr=new User();
    rolee:string;
    msg='';
  constructor(private _service : LoginserviceService,private auth : AuthenticateService,private router: Router) { }

  ngOnInit() {
  }
  
  loginUser(){
    this._service.loginUser(this.user).subscribe(
    data=>{
      console.log("response received");
      this.userr=data;
      sessionStorage.setItem("loggedInUser",JSON.stringify(this.userr));
      if( this.auth.authenticate(this.userr)){
        if(this.userr.role=="Admin")
        this.router.navigate(['/admin'])
        if(this.userr.role=="Customer")
        this.router.navigate(['/customer'])
    }
      else
      this.router.navigate(['/login'])
    },
    error=>{
      console.log("exception occured")
      this.msg="Bad Credentials! Please enter valid username/Password";
    })
  }
}
