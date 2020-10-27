import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from 'src/models/login.model';
import { UserModel } from 'src/models/user.model';
import { LoginService } from 'src/service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login : Login;
  user : UserModel;
  
  constructor(private route: Router, private loginservice: LoginService) { 
    this.login = new Login();
    this.user = new UserModel();
  }

  ngOnInit() {
  }

  checkRole(user: UserModel) {

    if (user == null) {
      alert("Invalid Username and Password");
    }
    sessionStorage.setItem('user', this.user.userId);
    console.log(user.role);

    if (user.role == "admin") {

      this.route.navigate(['admin']);

    }else if (user.role == "user") {
      
      this.route.navigate(['user']);
     
    } else {
      alert("You are not registered!")
    }
  }


  checkLogin() {
    console.log(this.login);
    this.loginservice.getUser(this.login).subscribe(data => {
      this.user = data;
      this.checkRole(this.user);
    });
  }

}
