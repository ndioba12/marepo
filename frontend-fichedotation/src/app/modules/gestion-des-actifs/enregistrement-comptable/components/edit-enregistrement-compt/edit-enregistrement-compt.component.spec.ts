import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditEnregistrementComptComponent } from './edit-enregistrement-compt.component';

describe('EditEnregistrementComptComponent', () => {
  let component: EditEnregistrementComptComponent;
  let fixture: ComponentFixture<EditEnregistrementComptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditEnregistrementComptComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditEnregistrementComptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
