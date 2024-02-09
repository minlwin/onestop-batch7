import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AttendanceComponent } from './pages/attendance/attendance.component';
import { LeaveComponent } from './pages/leave/leave.component';
import { PayrollComponent } from './pages/payroll/payroll.component';
import { EmployeeComponent } from './pages/employee/employee.component';
import { PositionComponent } from './pages/position/position.component';
import { AllowanceComponent } from './pages/allowance/allowance.component';
import { DeductionComponent } from './pages/deduction/deduction.component';
import { HolidayComponent } from './pages/holiday/holiday.component';
import { AttendanceEditComponent } from './pages/attendance-edit/attendance-edit.component';

export const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'attendance', children: [
    {path: 'list', component: AttendanceComponent},
    {path: 'edit', component: AttendanceEditComponent},
    {path: '', redirectTo: '/attendance/list', pathMatch: 'full'}
  ]},
  {path: 'leave', component: LeaveComponent},
  {path: 'payroll', component: PayrollComponent},
  {path: 'employee', component: EmployeeComponent},
  {path: 'position', component: PositionComponent},
  {path: 'allowance', component: AllowanceComponent},
  {path: 'deduction', component: DeductionComponent},
  {path: 'holiday', component: HolidayComponent},
  {path: '', redirectTo: '/home', pathMatch: 'full'}
];
