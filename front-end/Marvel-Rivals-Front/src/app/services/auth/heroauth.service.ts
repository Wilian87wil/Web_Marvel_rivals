import { Injectable } from '@angular/core';
import { Hero } from '../../models/hero';

@Injectable({
  providedIn: 'root'
})
 
export class HeroauthService {

 

  constructor() { }

  SetHero(hero:Hero){
    console.log("Guardando Hero")
    console.log(hero)
        sessionStorage.setItem('hero',JSON.stringify(hero,replacer))
  }

  GetHero():any{
    const hero =sessionStorage.getItem('hero');
    if(hero){
      return JSON.parse(hero,reviver);
    }else{
      return null
    }
  }

  HeroExist():boolean{
    const hero=this.GetHero();
    if(hero!==null){
      return true;
    }else{
      return false;
    }
  }
}
//Serializador the Map in Json
function replacer(key:string,value:any){
  if(value instanceof Map){
    return{
      data:Array.from(value.entries()),
      __type:"Map"
    };
  }
  return value;
}


 //Deserializar the Array for converter in Map
 function reviver(key:string,value:any){
  if(value && value.__type==='Map'){
    return new Map(value.data);
  }
  return value;
 }
