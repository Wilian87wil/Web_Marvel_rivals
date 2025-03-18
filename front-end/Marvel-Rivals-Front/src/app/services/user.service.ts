import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl="http://localhost:8080"
  constructor(private http:HttpClient) { }

  findUser(user:User):Observable<boolean>{
    return this.http.post<boolean>(`${this.baseUrl}/buscar-User`,user)
  }
}