import { NgModule } from '@angular/core';
import { AuthGuard } from './auth.guard';
import { Routes, RouterModule } from '@angular/router';
import { AddCenterComponent } from './add-center/add-center.component';
import { AddTestComponent } from './add-test/add-test.component';
import { AdminDashBoardComponent } from './admin-dash-board/admin-dash-board.component';
import { ListAppointmentsComponent } from './list-appointments/list-appointments.component';
import { ListCenterComponent } from './list-center/list-center.component';
import { ListTestComponent } from './list-test/list-test.component';
import { LoginComponent } from './login/login.component';
import { UserDashBoardComponent } from './user-dash-board/user-dash-board.component';
import { UserListCenterComponent } from './user-list-center/user-list-center.component';

import { ViewStatusComponent } from './view-status/view-status.component';
import { PersonalDetailsComponent } from './personal-details/personal-details.component';
import { PendingRequestComponent } from './pending-request/pending-request.component';
import { UserListTestComponent } from './user-list-test/user-list-test.component';

const routes: Routes = [
  { path: '', component: LoginComponent},
  { path: 'login', component: LoginComponent },

  {
    path: 'admin/dashboard', component: AdminDashBoardComponent, children: [
      { path: 'test/add', component: AddTestComponent },
      { path: 'test/view', component: ListTestComponent },
      { path: 'center/add', component: AddCenterComponent },
      { path: 'center/view', component: ListCenterComponent },
      { path: 'appointments/view', component: ListAppointmentsComponent },
      { path: 'appointments/pending', component: PendingRequestComponent }
    ], canActivate: [AuthGuard]
  },

  {
    path: 'user/dashboard', component: UserDashBoardComponent, children: [
      { path: 'userCenter/view', component: UserListCenterComponent },
      { path: 'selectTest/:centerId', component: UserListTestComponent },
      { path: 'viewStatus/:appointmentId', component: ViewStatusComponent },
      { path: 'PersonalDetails/:userId', component: PersonalDetailsComponent }
    ], canActivate: [AuthGuard]
  },
  // { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


