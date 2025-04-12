import { Stats } from './../models/Stats';
import { Img } from './../models/img';
import { HeroePoder} from './../models/heroepoder';
export class Hero{
    id!:number;
    nombre!:String;
    rol!:String;
    descripcion!:String;
    lore!:String;
    url_image!:String;
    url_video!:String;
    stats!:Stats[];
    urls_img!:Img;
    heroePoder!:HeroePoder;
}