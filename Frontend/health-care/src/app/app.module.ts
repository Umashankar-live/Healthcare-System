import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from "@angular/common/http";

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { Routes, RouterModule } from "@angular/router";
import { RegisterComponent } from './register/register.component';
import { FormsModule } from '@angular/forms';
import { CustomerComponent } from './customer/customer.component';
import { AdminComponent } from './admin/admin.component';
import { AuthGuardCustomerService } from './service/auth-guard-customer.service';
import { AuthGuardAdminService } from './service/auth-guard-admin.service';

const routes: Routes = [
  { path:'',redirectTo: '/login', pathMatch: 'full'},
  { path:'login',component:LoginComponent},
  { path:'register',component:RegisterComponent},
  { path:'customer',component:CustomerComponent,canActivate :[AuthGuardCustomerService]},
  { path:'admin',component: AdminComponent,canActivate :[AuthGuardAdminService]}
]

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    CustomerComponent,
    AdminComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
