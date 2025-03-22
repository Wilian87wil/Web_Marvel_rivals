import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl="http://localhost:8080"
  constructor(private http:HttpClient) { }

  findUser(user:User):Observable<User>{
    return this.http.post<User>(`${this.baseUrl}/find-user`,user)
  }

  CreateUser(user:User):Observable<User>{
    return this.http.post<User>(`${this.baseUrl}/register-user`,user);
  }
}