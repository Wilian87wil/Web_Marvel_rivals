import { Component, OnInit } from '@angular/core';
import { HeroauthService } from '../../services/auth/heroauth.service';
import { Hero } from '../../models/hero';

@Component({
  selector: 'app-hero.descripcion',
  imports: [],
  templateUrl: './hero.descripcion.component.html',
  styleUrl: './hero.descripcion.component.css'
})
export class HeroDescripcionComponent implements OnInit{
  hero!:Hero;
  constructor(private servicesHeroSession:HeroauthService){

  }
  ngOnInit(): void {
      this.hero=this.servicesHeroSession.GetHero();
      console.log(this.hero.urls_img.logo_url);
      
  }
}
