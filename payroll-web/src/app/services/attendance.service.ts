import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import attendances from '../../assets/dummy/attendance.json';

@Injectable({
  providedIn: 'root'
})
export class AttendanceService {

  constructor() { }

  search():Observable<any[]> {
    return of(attendances)
  }
}
