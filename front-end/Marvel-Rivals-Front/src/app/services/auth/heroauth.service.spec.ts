import { TestBed } from '@angular/core/testing';

import { HeroauthService } from './../auth/heroauth.service';

describe('HeroauthService', () => {
  let service: HeroauthService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HeroauthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
