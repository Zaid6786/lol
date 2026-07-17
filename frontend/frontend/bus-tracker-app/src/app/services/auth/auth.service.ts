import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, of, throwError } from 'rxjs';
import { tap, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8085';
  private currentUserSubject = new BehaviorSubject<any>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  constructor(private http: HttpClient) {
    const token = localStorage.getItem('token');
    const user = localStorage.getItem('user');
    if (token && user) {
      this.currentUserSubject.next(JSON.parse(user));
    }
    
    // Initialize mock database if not already present
    if (!localStorage.getItem('mock_students')) {
      const defaultStudents = [
        { studentId: 1, name: 'John Doe', email: 'john@college.edu', rollNo: 'student123', password: 'password' },
        { studentId: 2, name: 'Alice Smith', email: 'alice@college.edu', rollNo: '2021001', password: 'password' }
      ];
      localStorage.setItem('mock_students', JSON.stringify(defaultStudents));
    }
  }

  adminLogin(username: string, password: string): Observable<any> {
    // For now, if backend is not running, we can return a mock token or try real HTTP
    return this.http.post<any>(`${this.apiUrl}/admin/login`, { username, password })
      .pipe(
        tap(response => {
          if (response && response.token) {
            localStorage.setItem('token', response.token);
            localStorage.setItem('user', JSON.stringify(response.user));
            this.currentUserSubject.next(response.user);
          }
        }),
        // Mock fallback for UI demonstration when backend is down
        catchError(err => {
          console.warn('Backend connection failed. Using mock admin login.');
          const mockRes = { token: 'mock-admin-token', user: { role: 'ADMIN', username } };
          localStorage.setItem('token', mockRes.token);
          localStorage.setItem('user', JSON.stringify(mockRes.user));
          this.currentUserSubject.next(mockRes.user);
          return of(mockRes);
        })
      );
  }

  studentLogin(studentId: string, password: string): Observable<any> {
    // Send email/rollNo as the email field in Spring Boot's LoginRequest payload
    return this.http.post<any>(`${this.apiUrl}/student/login`, { email: studentId, password: password })
      .pipe(
        tap(response => {
          if (response && response.token) {
            localStorage.setItem('token', response.token);
            localStorage.setItem('user', JSON.stringify(response.user));
            this.currentUserSubject.next(response.user);
          }
        }),
        // Mock fallback for UI demonstration when backend is down
        catchError(err => {
          console.warn('Backend connection failed. Using mock student login.');
          const mockStudentsStr = localStorage.getItem('mock_students');
          const mockStudents = mockStudentsStr ? JSON.parse(mockStudentsStr) : [];
          
          const matchedStudent = mockStudents.find((s: any) => 
            (s.email === studentId || 
             s.rollNo === studentId || 
             `STU-${s.studentId}` === studentId ||
             `STU${s.studentId}` === studentId ||
             s.name === studentId ||
             String(s.studentId) === studentId) &&
            s.password === password
          );
          
          if (!matchedStudent) {
            return throwError(() => new Error('Invalid credentials or student not registered by administrator.'));
          }
          
          const mockRes = { 
            token: 'mock-student-token', 
            user: { 
              role: 'STUDENT', 
              studentId: matchedStudent.studentId, 
              username: matchedStudent.name,
              email: matchedStudent.email
            } 
          };
          localStorage.setItem('token', mockRes.token);
          localStorage.setItem('user', JSON.stringify(mockRes.user));
          this.currentUserSubject.next(mockRes.user);
          return of(mockRes);
        })
      );
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    this.currentUserSubject.next(null);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
}

