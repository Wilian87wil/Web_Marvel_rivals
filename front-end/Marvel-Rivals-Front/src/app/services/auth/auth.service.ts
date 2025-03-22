import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  setUser(user:any){
    localStorage.setItem("user",JSON.stringify(user));
  }

  getUser():any{
    const userString = localStorage.getItem('user');
    if(userString){
      return JSON.parse(userString)
    }
    return null;
  }

  isLoggedIn(): boolean {
    const user = this.getUser(); 
  
    if (user !== null) {
      
      return true; 
    } else {
      
      return false; 
    }
  }
  

  logout(){
    localStorage.removeItem("user");
  }
}
