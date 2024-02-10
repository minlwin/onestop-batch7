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
import { LeaveEditComponent } from './pages/leave-edit/leave-edit.component';
import { LeaveDetailsComponent } from './pages/leave-details/leave-details.component';
import { PayrollDetailsComponent } from './pages/payroll-details/payroll-details.component';
import { EmployeeEditComponent } from './pages/employee-edit/employee-edit.component';
import { EmployeeDetailsComponent } from './pages/employee-details/employee-details.component';
import { PositionEditComponent } from './pages/position-edit/position-edit.component';
import { AllowanceEditComponent } from './pages/allowance-edit/allowance-edit.component';
import { DeductionEditComponent } from './pages/deduction-edit/deduction-edit.component';
import { HolidayEditComponent } from './pages/holiday-edit/holiday-edit.component';
import { DepartmentComponent } from './pages/department/department.component';
import { DepartmentEditComponent } from './pages/department-edit/department-edit.component';
import { DepartmentDetailsComponent } from './pages/department-details/department-details.component';

export const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'department', children: [
    {path: 'list', component: DepartmentComponent},
    {path: 'edit', component: DepartmentEditComponent},
    {path: 'details', component: DepartmentDetailsComponent},
    {path: '', redirectTo: '/department/list', pathMatch: 'full'}
  ]},
  {path: 'employee', children: [
    {path: 'list', component: EmployeeComponent},
    {path: 'edit', component: EmployeeEditComponent},
    {path: 'details', component: EmployeeDetailsComponent},
    {path: '', redirectTo: '/employee/list', pathMatch: 'full'}
  ]},
  {path: 'attendance', children: [
    {path: 'list', component: AttendanceComponent},
    {path: 'edit', component: AttendanceEditComponent},
    {path: '', redirectTo: '/attendance/list', pathMatch: 'full'}
  ]},
  {path: 'leave', children: [
    {path: 'list', component: LeaveComponent},
    {path: 'edit', component: LeaveEditComponent},
    {path: 'details', component: LeaveDetailsComponent},
    {path: '', redirectTo: '/leave/list', pathMatch: 'full'}
  ]},
  {path: 'payroll', children: [
    {path: 'list', component: PayrollComponent},
    {path: 'details', component: PayrollDetailsComponent},
    {path: '', redirectTo: '/payroll/list', pathMatch: 'full'}
  ]},
  {path: 'position', children: [
    {path: 'list', component: PositionComponent},
    {path: 'edit', component: PositionEditComponent},
    {path: '', redirectTo: '/position/list', pathMatch: 'full'}
  ]},
  {path: 'allowance', children: [
    {path: 'list', component: AllowanceComponent},
    {path: 'edit', component: AllowanceEditComponent},
    {path: '', redirectTo: '/allowance/list', pathMatch: 'full'}
  ]},
  {path: 'deduction', children: [
    {path: 'list', component: DeductionComponent},
    {path: 'edit', component: DeductionEditComponent},
    {path: '', redirectTo: '/deduction/list', pathMatch: 'full'}
  ]},
  {path: 'holiday', children: [
    {path: 'list', component: HolidayComponent},
    {path: 'edit', component: HolidayEditComponent},
    {path: '', redirectTo: '/holiday/list', pathMatch: 'full'}
  ]},
  {path: '', redirectTo: '/home', pathMatch: 'full'}
];
