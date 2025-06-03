import { Stats } from './../models/stats';
import { Img } from './../models/img';
import { HeroePoder} from './../models/heroepoder';
import { Color } from './colors';
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
    colors!:Color;
}