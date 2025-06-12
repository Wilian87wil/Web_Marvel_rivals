import { Component, OnInit } from '@angular/core';
import { HeroService } from '../../services/hero/hero.service';
import { Hero } from '../../models/hero';
import { CommonModule } from '@angular/common';
import { Route, Router } from '@angular/router';
import { HeroauthService } from '../../services/auth/heroauth.service';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-home',
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
  heros:Hero[]=[];
  hero!:Hero;
  hover:number|null=null;
  herosSave:Hero[]=[];

  constructor(private servicesHero:HeroService,private router:Router,private heroauthservices:HeroauthService,private authser:AuthService){
  }

  ngOnInit(): void {
      this.servicesHero.heroAll().subscribe({
        next:(respuesta)=>{
          this.heros=respuesta;                  
          this.herosSave=respuesta;
        },
        error:(error)=>{}
      })
  }

  selectHero(hero:Hero){
    
    this.heroauthservices.SetHero(hero);
    this.router.navigate(["/hero-descripcion"])
  }

  SearchHero(event:Event){
    const input =event.target as HTMLInputElement;
    if(input.value===''){
      this.heros=this.herosSave;      
    }else{
    this.heros = this.heros.filter(h => 
    h.nombre.toLowerCase().includes(input.value)
    );
    }
  }
}
