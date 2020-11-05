import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { HttpClientModule } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {NgxPaginationModule} from 'ngx-pagination';
import { AppRoutingModule } from './app-routing.module';

import { CustomerComponent } from './customer/customer.component';
import { AddTestComponent } from './add-test/add-test.component';
import { ListTestComponent } from './list-test/list-test.component';
import { AddCenterComponent } from './add-center/add-center.component';
import { ListCenterComponent } from './list-center/list-center.component';
import { ListAppointmentsComponent } from './list-appointments/list-appointments.component';
import { AdminDashBoardComponent } from './admin-dash-board/admin-dash-board.component';
import { UserDashBoardComponent } from './user-dash-board/user-dash-board.component';
import { UserListCenterComponent } from './user-list-center/user-list-center.component';
import { MakeAppointmentComponent } from './make-appointment/make-appointment.component';
import { ViewStatusComponent } from './view-status/view-status.component';
import { PersonalDetailsComponent } from './personal-details/personal-details.component';
import { PendingRequestComponent } from './pending-request/pending-request.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,

    AppComponent,
    LoginComponent,

    CustomerComponent,
    AddTestComponent,
    ListTestComponent,
    AddCenterComponent,
    ListCenterComponent,
    ListAppointmentsComponent,
    AdminDashBoardComponent,
    UserDashBoardComponent,
    UserListCenterComponent,
    MakeAppointmentComponent,
    ViewStatusComponent,
    PersonalDetailsComponent,
    PendingRequestComponent
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
