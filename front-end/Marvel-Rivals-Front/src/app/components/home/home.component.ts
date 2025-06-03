import { Component, OnInit } from '@angular/core';
import { HeroService } from '../../services/hero/hero.service';
import { Hero } from '../../models/hero';
import { CommonModule } from '@angular/common';
import { Route, Router } from '@angular/router';
import { HeroauthService } from '../../services/auth/heroauth.service';

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

  constructor(private servicesHero:HeroService,private router:Router,private heroauthservices:HeroauthService){
  }

  ngOnInit(): void {
    //
      this.servicesHero.heroAll().subscribe({

        next:(respuesta)=>{
          this.heros=respuesta;
                    
        },

        error:(error)=>{}
      })
      
  }

  selectHero(hero:Hero){
    
    this.heroauthservices.SetHero(hero);
    this.router.navigate(["/hero-descripcion"])
  }
}
