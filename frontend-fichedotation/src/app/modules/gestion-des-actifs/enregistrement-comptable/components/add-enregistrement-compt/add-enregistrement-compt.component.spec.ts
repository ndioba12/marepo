import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEnregistrementComptComponent } from './add-enregistrement-compt.component';

describe('AddEnregistrementComptComponent', () => {
  let component: AddEnregistrementComptComponent;
  let fixture: ComponentFixture<AddEnregistrementComptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEnregistrementComptComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddEnregistrementComptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
