import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { heroguardGuard } from './heroguard.guard';

describe('heroguardGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => heroguardGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
