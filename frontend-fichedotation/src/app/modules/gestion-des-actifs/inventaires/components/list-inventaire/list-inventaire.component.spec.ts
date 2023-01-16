import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListInventaireComponent } from './list-inventaire.component';

describe('ListInventaireComponent', () => {
  let component: ListInventaireComponent;
  let fixture: ComponentFixture<ListInventaireComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListInventaireComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListInventaireComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
