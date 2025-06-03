import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { last, map, Observable } from 'rxjs';
import { Hero } from '../../models/hero';
import { Stats } from '../../models/stats';
import { HeroePoder } from '../../models/heroepoder';
import { Poder } from '../../models/poder';
import { TypeAttack } from '../../models/typeattacks';
import { Img } from '../../models/img';
import { Color } from '../../models/colors';

@Injectable({
  providedIn: 'root'
})
export class HeroService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  heroforid(id: number): Observable<Hero> {
    return this.http.get<any>(`${this.baseUrl}/buscar/${id}`).pipe(
      map((data:any)=>{
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
        hero.colors= new Color();
        hero.colors.color_1=data.color.color_1;
        hero.colors.color_2=data.color.color_2;

        
        hero.urls_img = new Img();
        hero.urls_img.fondo_url = data.urls_img.fondo_url;
        hero.urls_img.logo_url = data.urls_img.logo_url;
        hero.urls_img.logo_rol_url = data.urls_img.logo_rol_url;
        hero.urls_img.personaje_url = data.urls_img.personaje_url;
        hero.urls_img.url_img_logo_pj = data.urls_img.url_img_logo_pj;


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


  heroAll():Observable<Array<Hero>>{

    return this.http.get<Array<any>>(`${this.baseUrl}/findAll`).pipe(
      map(heroes=>heroes.map(heroemodify=>{
        const heroe = new Hero();
        heroe.id=heroemodify.id;
        heroe.nombre=heroemodify.nombre;
        heroe.rol=heroemodify.rol;
        heroe.descripcion=heroemodify.descripcion;
        heroe.lore=heroemodify.lore;
        heroe.url_image=heroemodify.url_image;
        heroe.url_video=heroemodify.url_video;

        heroe.stats=heroemodify.stats.map((s:any)=>{
          const stats= new Stats()
          stats.id=s.id;
          stats.nombre=s.nombre;
          stats.cantidad=s.cantidad;
          return stats;
        })

        heroe.urls_img = new Img();
        heroe.urls_img.fondo_url = heroemodify.urls_img.fondo_url;
        heroe.urls_img.logo_url = heroemodify.urls_img.logo_url;
        heroe.urls_img.logo_rol_url = heroemodify.urls_img.logo_rol_url;
        heroe.urls_img.personaje_url = heroemodify.urls_img.personaje_url;
        heroe.urls_img.url_img_logo_pj = heroemodify.urls_img.url_img_logo_pj;

        heroe.colors= new Color();
        heroe.colors.color_1=heroemodify.color.color_1;
        heroe.colors.color_2=heroemodify.color.color_2;

        const heroePoder = new HeroePoder();
        heroePoder.id=heroemodify.heroePoder.id;

        const poderes:Poder[]=heroemodify.heroePoder.poderes.map((p:any)=>{
          const poder = new Poder();
          poder.nombre=p.nombre;
          poder.extra= new Map<string,TypeAttack[]>();
          Object.entries(p.extra).forEach(([llave,valor]:[string,any])=>{
            if(valor instanceof Array){
              const typeattacks:TypeAttack[]=[];
              valor.forEach(val=>{
                const typeattack=new TypeAttack()
                typeattack.name=val.name;
                typeattack.key=val.key;
                typeattack.url_image=val.url_image;
                if(val.stats instanceof Array){
                  typeattack.stats=val.stats;
                }
                typeattacks.push(typeattack);
              });
             poder.extra.set(llave,typeattacks) 
            }
          })
          return poder;
        })
        heroePoder.poderes=poderes;
        heroe.heroePoder=heroePoder;
        return heroe;
      }))
    )

  }

}