import { inject, Inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { HeroauthService } from '../auth/heroauth.service';

export const heroguardGuard: CanActivateFn = (route, state) => {
  const router=inject(Router);
  const heroauth=inject(HeroauthService)
  if(!heroauth.HeroExist()){
    router.navigate(["/home"])
    return false; 
  }
  return true;
};
