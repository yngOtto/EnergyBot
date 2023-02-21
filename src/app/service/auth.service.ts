import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { response } from 'express';
import { catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';



@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private baseUrl = 'http://localhost:8080/api/v1/user';
  constructor(private http: HttpClient) { }

  login(email: string, password: string): Observable<any> {
    const url = `${this.baseUrl}/login`;
    const body = { email, password };
    return this.http.post<any>(url, body).pipe(
      catchError(this.handleError)
    );
  }

  confirm(token: string): Observable<any> {
    const url = `${this.baseUrl}/confirm`;
    const body = {token};
    return this.http.post<any>(url, body).pipe(
      catchError(this.handleError)
    );
  }

  register(email: string, password: string): Observable<any> {
    const url = `${this.baseUrl}/register`;
    const body = { email, password };
    return this.http.post<any>(url, body).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: any) {
    console.error(error);
    return throwError(error);
  }
}
