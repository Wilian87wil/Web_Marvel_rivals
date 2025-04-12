import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { last, map, Observable } from 'rxjs';
import { Hero } from '../../models/hero';
import { Stats } from '../../models/Stats';
import { HeroePoder } from '../../models/heroepoder';
import { Poder } from '../../models/poder';
import { TypeAttack } from '../../models/typeattacks';
import { Img } from '../../models/img';

@Injectable({
  providedIn: 'root'
})
export class HeroService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  heroTest(id: number): Observable<Hero> {
    return this.http.get<any>(`${this.baseUrl}/buscar/${id}`).pipe(
      map(data=>{
        const hero=new Hero();
        hero.id=data.id;
        hero.nombre=data.nombre;
        hero.rol=data.rol;
        hero.descripcion=data.descripcion;
        hero.lore=data.lore;
        hero.url_image=data.url_image;
        hero.url_video=data.url_video;

        hero.stats=data.stats.map((s:any)=>{
          const stats=new Stats();
          stats.id=s.id;
          stats.nombre=s.nombre;
          stats.cantidad=s.cantidad;
          return stats;
        });

        hero.urls_img = new Img();
        hero.urls_img.fondo_url = data.urls_img.fondo_url;
        hero.urls_img.logo_url = data.urls_img.logo_url;
        hero.urls_img.logo_rol_url = data.urls_img.logo_rol_url;
        hero.urls_img.personaje_url = data.urls_img.personaje_url;


        const heroePoder= new HeroePoder();
        heroePoder.id=data.heroePoder.id;

        const poderes:Poder[]=data.heroePoder.poderes.map((poder:any)=>{
          const poderx=new Poder();
          poderx.nombre=poder.nombre;
          poderx.extra=new Map<string,TypeAttack[]>();
          Object.entries(poder.extra).forEach(([llave,valor]:[string,any])=>{
            if(valor instanceof Array){
              const typeattacks:TypeAttack[]=[];
              valor.forEach(val=>{
              const typeattack:TypeAttack=new TypeAttack();
              typeattack.name=val.name;
              typeattack.key=val.key;
              typeattack.url_image=val.url_image;
              if(val.stats instanceof Array){
                typeattack.stats=val.stats;
              }
              typeattacks.push(typeattack);
              });
              poderx.extra.set(llave,typeattacks);
            }
          });

          return poderx
        });

        heroePoder.poderes=poderes;
        hero.heroePoder=heroePoder;
        return hero;
      })
    );
  
  }
}