import { TypeAttack } from './../models/typeattacks';
export class Poder{
    nombre!:string;
    extra!:Map<string,TypeAttack[]>
}