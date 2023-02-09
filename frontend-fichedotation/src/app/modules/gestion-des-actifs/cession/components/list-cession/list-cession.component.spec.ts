import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCessionComponent } from './list-cession.component';

describe('ListCessionComponent', () => {
  let component: ListCessionComponent;
  let fixture: ComponentFixture<ListCessionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListCessionComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListCessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
