import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroDescripcionComponent } from './hero.descripcion.component';

describe('HeroDescripcionComponent', () => {
  let component: HeroDescripcionComponent;
  let fixture: ComponentFixture<HeroDescripcionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HeroDescripcionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeroDescripcionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
