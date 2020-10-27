import { Injectable } from '@angular/core';
import { Login } from 'src/models/login.model';
import { UserModel } from 'src/models/user.model';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(public http : HttpClient) { }

  getUser(login : Login){
    console.log(login);
    return this.http.post<UserModel>("http://localhost:9002/login/validate",login);
  }
}
