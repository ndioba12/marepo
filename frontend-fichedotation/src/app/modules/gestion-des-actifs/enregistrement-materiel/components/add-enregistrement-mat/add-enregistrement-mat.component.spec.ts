import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEnregistrementMatComponent } from './add-enregistrement-mat.component';

describe('AddEnregistrementMatComponent', () => {
  let component: AddEnregistrementMatComponent;
  let fixture: ComponentFixture<AddEnregistrementMatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEnregistrementMatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddEnregistrementMatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
