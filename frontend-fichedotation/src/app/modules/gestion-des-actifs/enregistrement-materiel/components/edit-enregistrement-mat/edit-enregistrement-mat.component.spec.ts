import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditEnregistrementMatComponent } from './edit-enregistrement-mat.component';

describe('EditEnregistrementMatComponent', () => {
  let component: EditEnregistrementMatComponent;
  let fixture: ComponentFixture<EditEnregistrementMatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditEnregistrementMatComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditEnregistrementMatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
