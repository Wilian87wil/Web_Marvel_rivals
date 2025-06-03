import { Component, OnInit } from '@angular/core';
import { HeroauthService } from '../../services/auth/heroauth.service';
import { Hero } from '../../models/hero';
import { CommonModule } from '@angular/common';
import { TypeAttack } from '../../models/typeattacks';
import { Stats } from '../../models/stats';

@Component({
  selector: 'app-hero.descripcion',
  imports: [CommonModule],
  templateUrl: './hero.descripcion.component.html',
  styleUrl: './hero.descripcion.component.css'
})
export class HeroDescripcionComponent implements OnInit{
  hero!:Hero;
  typeattack!:TypeAttack;
  hover!:number|null;
  hover2!:number|null;
  hover3!:number|null;
  text_descrip:string='Base Stats';
  text_descrip_2:string|null=null;
  stats!:Array<Stats>|null;
  stats_attack!:Array<[string,object]>;
  constructor(private servicesHeroSession:HeroauthService){

  }
  ngOnInit(): void {
      this.hero=this.servicesHeroSession.GetHero();
      this.stats=this.hero.stats;
      console.log(this.hero.urls_img.logo_url);
      
      
  }

  cuadro_description(name:string,key:string,stats:Array<Stats>|null,stats_attack:Map<string,object>|null){
    if(name==='Base Stats' && key===''){
      this.text_descrip=name;
      this.text_descrip_2=null;
      this.stats=this.hero.stats;
    }else{
      this.text_descrip=name;
      this.text_descrip_2=key;
      this.stats=null;
      if(stats_attack){
        this.stats_attack=Array.from(Object.entries(stats_attack));
        console.log(stats_attack);
        
      }
      
    }
    
  }

  getmargin(nombre:string):string{
    if(nombre.length<10){
      return '900px'
    }
    if(nombre.length>10 && nombre.length<50){
      return '1290'
    }
    return '1390'
  }
}
