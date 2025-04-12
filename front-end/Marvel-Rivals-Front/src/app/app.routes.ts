import { Routes } from '@angular/router';
import { InicioComponent } from './components/inicio/inicio.component';
import { HomeComponent } from './components/home/home.component';
import { authGuard } from './services/guard/auth.guard';
import { HeroDescripcionComponent } from './components/hero-descripcion/hero.descripcion.component';
import { heroguardGuard } from './services/guard/heroguard.guard';

export const routes: Routes = [

    {path:'inicio',component:InicioComponent},
    {path:'home',component:HomeComponent,canActivate:[authGuard]},
    {path:'hero-descripcion',component:HeroDescripcionComponent,canActivate:[heroguardGuard,authGuard]},
    {path:'',redirectTo:'inicio',pathMatch:'full'},
    {path:'**',redirectTo:'inicio'}
];
