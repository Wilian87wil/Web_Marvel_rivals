import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user/user.service';
import { Route, Router } from '@angular/router';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-inicio',
  imports: [CommonModule,FormsModule],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css'
})
export class InicioComponent implements OnInit{
  //mostrar register
  showRegister=false;
  //mostrar panel de error
  hidenerr=false;
  //ocultar el login y el register para ver el video
  ocultar=false;
  //ocultar texto de login para mostar texto de register err
  mostrarErrMensaje=false;
  //Verificar si estas logeado
  userlogin=false;

  usuario=new User();
  audio=new Audio('/audio/audio-jeff.mp3');
  mensajeErr="";

  constructor(private services:UserService, private router:Router, private authservices:AuthService){}

  ngOnInit(): void {
      if(this.authservices.isLoggedIn()===true){
        this.userlogin=true;
      }else{
        this.userlogin=false;
      }
  }

  //Ocultar register y login para ver el video
  methodocultar(){
    console.log(this.authservices.isLoggedIn());
    this.ocultar=!this.ocultar;
  }
  
  //Logearte
  onSubmit():void{
    if(this.mostrarErrMensaje){
      this.mostrarErrMensaje=!this.mostrarErrMensaje;
    }
    this.findUser()
  }

  onSubmitRegister():void{
    if(this.usuario.nombre==null||this.usuario.email==null||this.usuario.password==null){
      this.mensajeErr="llene todos los campos porfavor"
      this.mostrarErrMensaje=true;
      this.hidenerr=true;
    }else{

      if(!this.usuario.email.match(/^([A-Za-z0-9._%+-]+)@([A-Za-z0-9.-]+)\.([A-Za-z]{2,})$/)){
        this.mensajeErr="El email debe ser valido";
        if(!this.mostrarErrMensaje){
          this.mostrarErrMensaje=true;
        }
        this.hidenerr=true
      }else if(this.usuario.nombre=="-->nuevo_nombre<--"){
        this.mensajeErr="El nombre debe ser valido";
        if(!this.mostrarErrMensaje){
          this.mostrarErrMensaje=true;
        }
        this.hidenerr=true
      }else{
        this.findUser()
      }
    }
  }
  
  findUser(){
    if(this.usuario.nombre==null){
       this.services.findUser(this.usuario).subscribe({
           next:(resultado)=>{
           this.usuario=resultado;
             this.usuario.email="";
             this.usuario.password="";
             this.userRegisterSuccessful()
         },
        error:(error)=>{
        this.hidenerr=true;
        }
        })
    }else{
      
      this.services.CreateUser(this.usuario).subscribe({
        next:(resultado)=>{
          this.usuario=resultado;
           this.usuario.email="";
           this.usuario.password="";
           this.userlogin=true;
           this.userRegisterSuccessful();
        },
        error:(err)=>{
          console.log("Ocurrio un Error");
          this.usuario=err.error;
          
          if(this.usuario.nombre!=null && this.usuario.nombre=="Ya existe el nombre que colocaste, utiliza otro porfavor"){
            this.mensajeErr=this.usuario.nombre;
            this.usuario.nombre="-->nuevo_nombre<--";
          }
          if(this.usuario.email!=null && this.usuario.email=="Ya existe el email que colocaste, utiliza otro porfavor"){
            this.mensajeErr=this.usuario.email;
            this.usuario.email="";
          }
          if(!this.mostrarErrMensaje){
            this.mostrarErrMensaje=true;
          }
          this.hidenerr=true;
        }
      })
    }
  }

  closeErr(){
    this.hidenerr=!this.hidenerr;
    this.audio.pause();
    const video = document.querySelector('video');
    if(video){
      video.muted=false;
    }
  }

  playAudio(){
    this.audio.play()
    const video = document.querySelector('video');
    if(video){
      video.muted=true;
    }
  }

  enableRegister(){
    this.showRegister=!this.showRegister;
    if(this.hidenerr=true){
      this.hidenerr=false;
    }
    this.usuario=new User()
  }

  userRegisterSuccessful():void{
    console.log("ENTRO AQUI");
    
    this.authservices.setUser(this.usuario);
    this.router.navigate(['/home']);
  }
}
