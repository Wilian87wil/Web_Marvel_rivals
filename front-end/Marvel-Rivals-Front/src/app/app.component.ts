import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet, Routes } from '@angular/router';
import { InicioComponent } from "./components/inicio/inicio.component";
import { UserService } from './services/user/user.service';
import { AuthService } from './services/auth/auth.service';
import { User } from './models/user';
import { CommonModule } from '@angular/common';
import { routes } from './app.routes';
import { HeroauthService } from './services/auth/heroauth.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet,CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{

  user!:User;
  constructor(private authuser:AuthService,private root:Router,private authhero:HeroauthService,private user_service:UserService){}

  ngOnInit(): void {
      const any=this.authuser.getUser();
      this.user=new User()
      if(any && any.nombre){
         this.user.nombre=any.nombre;         
      }
      
  }
  
  home(){
    if(this.user && this.user.nombre){
      this.root.navigate(['/home'])      
    }
  }

  heros(){
    if(this.authhero.HeroExist()){
      this.root.navigate(['/hero-descripcion'])
    }
  }

  logout(){
     Swal.fire({
      title: '¿Estás seguro?',
      text: 'Estas a punto de deslogearte.',
      imageUrl:'/extras/galacta2-icon.jpg',
      showCancelButton: true,
      confirmButtonText: 'Sí, deslogearte',
      cancelButtonText: 'Cancelar'
    }).then(result => {
      if (result.isConfirmed) {
        this.authuser.logout();
        this.authhero.DeleteHero();
        window.location.reload();      
      } else {
        Swal.fire('Cancelado', 'No se realizaron cambios.');
      }
      });
    
  }

  closeAccount(){
    Swal.fire({
      title:'¿Estás seguro?',
      text:'Estás a punto de Eliminar tu cuenta.',
      imageUrl:'/extras/galacta-icon.jpg',
      showCancelButton:true,
      confirmButtonText:'Eliminar',
      cancelButtonText:'Cancelar'
    }).then(result=>{
      if(result.isConfirmed){
        const usersave=this.authuser.getUser();
        this.user_service.DeleteUser(usersave.id).subscribe({
          next:(result:string)=>{
            this.authuser.logout();
            this.authhero.DeleteHero();
            window.location.reload(); 
          },
          error:(err)=>{
             Swal.fire({
              title:'Ocurrio un Problema',
              text:'Salio algo mal.',
              icon:'error',
              confirmButtonText:'Ok'
             })
          }
        })
      }else{
        Swal.fire('Cancelado', 'No se realizaron cambios.');
      }
    })
  }

  LoginWhitGoogle(){
    window.location.href='http://localhost:8080/oauth2/authorization/google';
  }
}
