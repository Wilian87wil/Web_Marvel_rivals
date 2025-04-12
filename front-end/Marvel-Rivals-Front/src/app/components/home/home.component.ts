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
      this.servicesHero.heroTest(38).subscribe({
        next:(respuesta)=>{
          this.hero=respuesta;
          
          this.heros.push(this.hero);          
        },
        error:(err)=>{},
      })

      const heroe=new Hero()
      heroe.nombre="spiderman"
      heroe.rol="duelista"
      heroe.lore="Es peter parket"
      heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spidy-marvel-rivals.mp4"
      heroe.descripcion="es spiderman"
      this.heros.push(heroe);
      heroe.nombre="spiderman"
      heroe.rol="duelista"
      heroe.lore="Es peter parket"
      heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spidy-marvel-rivals.mp4"
      heroe.descripcion="es spiderman"
      this.heros.push(heroe);
      heroe.nombre="spiderman"
      heroe.rol="duelista"
      heroe.lore="Es peter parket"
      heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spidy-marvel-rivals.mp4"
      heroe.descripcion="es spiderman"
      this.heros.push(heroe);
      heroe.nombre="spiderman"
      heroe.rol="duelista"
      heroe.lore="Es peter parket"
      heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spidy-marvel-rivals.mp4"
      heroe.descripcion="es spiderman"
      this.heros.push(heroe);
      heroe.nombre="spiderman"
      heroe.rol="duelista"
      heroe.lore="Es peter parket"
      heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spidy-marvel-rivals.mp4"
      heroe.descripcion="es spiderman"
      this.heros.push(heroe);
      heroe.nombre="spiderman"
      heroe.rol="duelista"
      heroe.lore="Es peter parket"
      heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spidy-marvel-rivals.mp4"
      heroe.descripcion="es spiderman"
      this.heros.push(heroe);
      heroe.nombre="spiderman"
      heroe.rol="duelista"
      heroe.lore="Es peter parket"
      heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spidy-marvel-rivals.mp4"
      heroe.descripcion="es spiderman"
      this.heros.push(heroe);
        heroe.nombre="spiderman"
        heroe.rol="duelista"
        heroe.lore="Es peter parket"
        heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spidy-marvel-rivals.mp4"
        heroe.descripcion="es spiderman"
        this.heros.push(heroe);
        heroe.nombre="spiderman"
        heroe.rol="duelista"
        heroe.lore="Es peter parket"
        heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spidy-marvel-rivals.mp4"
        heroe.descripcion="es spiderman"
        this.heros.push(heroe);
        heroe.nombre="spiderman"
        heroe.rol="duelista"
        heroe.lore="Es peter parket"
        heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spidy-marvel-rivals.mp4"
        heroe.descripcion="es spiderman"
        this.heros.push(heroe);
        heroe.nombre="spiderman"
        heroe.rol="duelista"
        heroe.lore="Es peter parket"
        heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spidy-marvel-rivals.mp4"
        heroe.descripcion="es spiderman"
        this.heros.push(heroe);
        heroe.nombre="spiderman"
        heroe.rol="duelista"
        heroe.lore="Es peter parket"
        heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spidy-marvel-rivals.mp4"
        heroe.descripcion="es spiderman"
        this.heros.push(heroe);
        heroe.nombre="spiderman"
        heroe.rol="duelista"
        heroe.lore="Es peter parket"
        heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spidy-marvel-rivals.mp4"
        heroe.descripcion="es spiderman"
        this.heros.push(heroe);
        heroe.nombre="spiderman"
        heroe.rol="duelista"
        heroe.lore="Es peter parket"
        heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spidy-marvel-rivals.mp4"
        heroe.descripcion="es spiderman"
        this.heros.push(heroe);
        heroe.nombre="iro-man"
        heroe.id=1
        heroe.rol="duelista"
        heroe.lore="Es peter parket"
        heroe.url_image="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spider-man-marvel-rivals.jpg"
        heroe.descripcion="es spiderman"
        heroe.url_video="https://wilian-rivals.s3.us-east-2.amazonaws.com/marvel-rivals/spiderman/spider-man-video.mp4";
        this.heros.push(heroe);
      
  }

  selectHero(hero:Hero){
    
    this.heroauthservices.SetHero(hero);
    this.router.navigate(["/hero-descripcion"])
  }
}
