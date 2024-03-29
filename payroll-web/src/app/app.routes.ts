import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AttendanceComponent } from './pages/management/attendance/attendance.component';
import { LeaveComponent } from './pages/management/leave/leave.component';
import { PayrollComponent } from './pages/management/payroll/payroll.component';
import { EmployeeComponent } from './pages/management/employee/employee.component';
import { PositionComponent } from './pages/master/position/position.component';
import { AllowanceComponent } from './pages/master/allowance/allowance.component';
import { DeductionComponent } from './pages/master/deduction/deduction.component';
import { HolidayComponent } from './pages/setting/holiday/holiday.component';
import { AttendanceEditComponent } from './pages/management/attendance/attendance-edit/attendance-edit.component';
import { LeaveEditComponent } from './pages/management/leave/leave-edit/leave-edit.component';
import { LeaveDetailsComponent } from './pages/management/leave/leave-details/leave-details.component';
import { PayrollDetailsComponent } from './pages/management/payroll/payroll-details/payroll-details.component';
import { EmployeeEditComponent } from './pages/management/employee/employee-edit/employee-edit.component';
import { EmployeeDetailsComponent } from './pages/management/employee/employee-details/employee-details.component';
import { PositionEditComponent } from './pages/master/position/position-edit/position-edit.component';
import { AllowanceEditComponent } from './pages/master/allowance/allowance-edit/allowance-edit.component';
import { DeductionEditComponent } from './pages/master/deduction/deduction-edit/deduction-edit.component';
import { HolidayEditComponent } from './pages/setting/holiday/holiday-edit/holiday-edit.component';
import { DepartmentComponent } from './pages/master/department/department.component';
import { DepartmentEditComponent } from './pages/master/department/department-edit/department-edit.component';
import { DepartmentDetailsComponent } from './pages/master/department/department-details/department-details.component';
import { SettingsComponent } from './pages/setting/settings/settings.component';
import { SettingsEditComponent } from './pages/setting/settings/settings-edit/settings-edit.component';
import { SigninComponent } from './pages/signin/signin.component';
import { ChangePasswordComponent } from './pages/setting/change-password/change-password.component';
import { PositionDetailsComponent } from './pages/master/position/position-details/position-details.component';
import { LeaveTypeComponent } from './pages/setting/leave-type/leave-type.component';
import { LeaveTypeEditComponent } from './pages/setting/leave-type/leave-type-edit/leave-type-edit.component';

export const routes: Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'signin', component: SigninComponent},
  {path: 'change-pass', component: ChangePasswordComponent},
  {path: 'management', children: [
    {path: 'employee', children: [
      {path: 'list', component: EmployeeComponent},
      {path: 'edit', component: EmployeeEditComponent},
      {path: 'details', component: EmployeeDetailsComponent},
      {path: '', redirectTo: '/management/employee/list', pathMatch: 'full'}
    ]},
    {path: 'attendance', children: [
      {path: 'list', component: AttendanceComponent},
      {path: 'edit', component: AttendanceEditComponent},
      {path: '', redirectTo: '/management/attendance/list', pathMatch: 'full'}
    ]},
    {path: 'leave', children: [
      {path: 'list', component: LeaveComponent},
      {path: 'edit', component: LeaveEditComponent},
      {path: 'details', component: LeaveDetailsComponent},
      {path: '', redirectTo: '/management/leave/list', pathMatch: 'full'}
    ]},
    {path: 'payroll', children: [
      {path: 'list', component: PayrollComponent},
      {path: 'details', component: PayrollDetailsComponent},
      {path: '', redirectTo: '/management/payroll/list', pathMatch: 'full'}
    ]},
  ]},
  {path: 'master', children: [
    {path: 'department', children: [
      {path: 'list', component: DepartmentComponent},
      {path: 'edit', component: DepartmentEditComponent},
      {path: 'details', component: DepartmentDetailsComponent},
      {path: '', redirectTo: '/master/department/list', pathMatch: 'full'}
    ]},
    {path: 'position', children: [
      {path: 'list', component: PositionComponent},
      {path: 'edit', component: PositionEditComponent},
      {path: 'details', component: PositionDetailsComponent},
      {path: '', redirectTo: '/master/position/list', pathMatch: 'full'}
    ]},
    {path: 'allowance', children: [
      {path: 'list', component: AllowanceComponent},
      {path: 'edit', component: AllowanceEditComponent},
      {path: '', redirectTo: '/master/allowance/list', pathMatch: 'full'}
    ]},
    {path: 'deduction', children: [
      {path: 'list', component: DeductionComponent},
      {path: 'edit', component: DeductionEditComponent},
      {path: '', redirectTo: '/master/deduction/list', pathMatch: 'full'}
    ]},
  ]},
  {path: 'settings', children: [
    {path: 'holiday', children: [
      {path: 'list', component: HolidayComponent},
      {path: 'edit', component: HolidayEditComponent},
      {path: '', redirectTo: '/settings/holiday/list', pathMatch: 'full'}
    ]},
    {path: 'leave-type', children: [
      {path: 'list', component: LeaveTypeComponent},
      {path: 'edit', component: LeaveTypeEditComponent},
      {path: '', redirectTo: '/settings/leave-type/list', pathMatch: 'full'}
    ]},
    {path: 'system-setting', children: [
      {path: 'details', component: SettingsComponent},
      {path: 'edit', component: SettingsEditComponent},
      {path: '', redirectTo: '/settings/system-setting/details', pathMatch: 'full'}
    ]}
  ]},
  {path: '', redirectTo: '/home', pathMatch: 'full'}
];
