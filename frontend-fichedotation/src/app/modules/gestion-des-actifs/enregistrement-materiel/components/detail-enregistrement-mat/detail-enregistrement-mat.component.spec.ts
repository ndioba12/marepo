import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailEnregistrementMatComponent } from './detail-enregistrement-mat.component';

describe('DetailEnregistrementMatComponent', () => {
  let component: DetailEnregistrementMatComponent;
  let fixture: ComponentFixture<DetailEnregistrementMatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailEnregistrementMatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailEnregistrementMatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
