import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListEnregistrementMatComponent } from './list-enregistrement-mat.component';

describe('ListEnregistrementMatComponent', () => {
  let component: ListEnregistrementMatComponent;
  let fixture: ComponentFixture<ListEnregistrementMatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListEnregistrementMatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListEnregistrementMatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
