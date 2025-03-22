import { Routes } from '@angular/router';
import { InicioComponent } from './components/inicio/inicio.component';
import { HomeComponent } from './components/home/home.component';
import { authGuard } from './services/guard/auth.guard';

export const routes: Routes = [

    {path:'inicio',component:InicioComponent},
    {path:'home',component:HomeComponent,canActivate:[authGuard]},
    {path:'',redirectTo:'inicio',pathMatch:'full'},
    {path:'**',redirectTo:'inicio'}
];
