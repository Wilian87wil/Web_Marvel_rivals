import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { FormsModule } from '@angular/forms';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-inicio',
  imports: [CommonModule,FormsModule],
  templateUrl: './inicio.component.html',
  styleUrl: './inicio.component.css'
})
export class InicioComponent{
  resultadoBusqueda!:boolean;
  ocultar=false;
  usuario=new User();

  constructor(private service:UserService){}

  methodocultar(){
    this.ocultar=!this.ocultar;
  }
  
  onSubmit():void{
    console.log('Datos del usuario: ' + this.usuario.nombre)
    this.findUser();
  }

  findUser():void{
    this.service.findUser(this.usuario).subscribe({
       next:(respuesta)=>{
        this.resultadoBusqueda=respuesta;
        console.log('El usuario fue encontrado '  + this.resultadoBusqueda);
        
       },
       error:(err)=>{
        console.log('No se encontro el usuario',err)
       }
    })
  }
  
}
