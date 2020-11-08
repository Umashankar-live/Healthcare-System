/* Angular’s route guards are interfaces which can tell the router whether or not it should allow navigation
 to a requested route. They make this decision by looking for a true or false return value from a class which 
 implements the given guard interface. */

 /* CanActivate is an Angular interface. 
It is used to force user to login into application before navigating to the route. */

import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/service/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private auth: AuthService, 
    private router: Router,) {

  }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    if(this.auth.isloggedin== false)
    alert("You are trying access the application in a wrong way.. please Login First !! ");
    return this.auth.isloggedin ;
      
  }
}
