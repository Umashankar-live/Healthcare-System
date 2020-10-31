import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './user/user.component';
import { HttpClientModule } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {NgxPaginationModule} from 'ngx-pagination';
import { AppRoutingModule } from './app-routing.module';
import { RegisterComponent } from './register/register.component';
import { CustomerComponent } from './customer/customer.component';
import { AddTestComponent } from './add-test/add-test.component';
import { ListTestComponent } from './list-test/list-test.component';
import { AddCenterComponent } from './add-center/add-center.component';
import { ListCenterComponent } from './list-center/list-center.component';
import { ListAppointmentsComponent } from './list-appointments/list-appointments.component';
import { AdminDashBoardComponent } from './admin-dash-board/admin-dash-board.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserComponent,
    RegisterComponent,
    AppComponent,
    LoginComponent,
    RegisterComponent,
    CustomerComponent,
    AddTestComponent,
    ListTestComponent,
    AddCenterComponent,
    ListCenterComponent,
    ListAppointmentsComponent,
    AdminDashBoardComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NgbModule,
    NgxPaginationModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
