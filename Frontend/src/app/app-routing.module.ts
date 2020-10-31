import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddCenterComponent } from './add-center/add-center.component';
import { AddTestComponent } from './add-test/add-test.component';
import { AdminDashBoardComponent } from './admin-dash-board/admin-dash-board.component';
import { CustomerComponent } from './customer/customer.component';
import { ListAppointmentsComponent } from './list-appointments/list-appointments.component';
import { ListCenterComponent } from './list-center/list-center.component';
import { ListTestComponent } from './list-test/list-test.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';



const routes: Routes = [
  { path:'',redirectTo: '/login', pathMatch: 'full'},
  { path:'login',component:LoginComponent},
  { path:'register',component:RegisterComponent},


  
  { 
    path:'admin/dashboard',component: AdminDashBoardComponent ,children: [
    { path:'test/add',component:AddTestComponent},
    { path:'test/view',component:ListTestComponent},
    { path:'center/add',component:AddCenterComponent},
    { path:'center/view',component:ListCenterComponent},
    { path:'appointments/view',component:ListAppointmentsComponent},
    ]
  },
  
  { path:'customer',component:CustomerComponent}
  
  // { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


