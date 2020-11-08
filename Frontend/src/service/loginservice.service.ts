import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user.model';
import { Login } from 'src/models/login.model';

@Injectable({
  providedIn: 'root'
})
export class LoginserviceService {




  login_url = "http://localhost:9002/healthcaresystem/login";
  registration_url = "http://localhost:9008/user/addUser";
  getUser_url = "http://localhost:9008/user/searchUser/"


  constructor(private http: HttpClient) { }

  public loginUser(login: Login) {
    return this.http.post<User>(this.login_url, login)
  }

  public registerUser(user: User): Observable<any> {
    return this.http.post<any>(this.registration_url, user)
  }


  public getUserByUserId(userId: string) {
    return this.http.get<User>(this.getUser_url + userId);
  }

  updateUser(user: User, userId: string) {
    return this.http.put<User>("http://localhost:9008/user/updateUser/"+userId, user);
  }

}
