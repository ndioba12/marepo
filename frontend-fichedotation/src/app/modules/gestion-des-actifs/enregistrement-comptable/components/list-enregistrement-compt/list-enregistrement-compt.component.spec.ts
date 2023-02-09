import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListEnregistrementComptComponent } from './list-enregistrement-compt.component';

describe('ListEnregistrementComptComponent', () => {
  let component: ListEnregistrementComptComponent;
  let fixture: ComponentFixture<ListEnregistrementComptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListEnregistrementComptComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListEnregistrementComptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
