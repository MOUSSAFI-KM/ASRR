import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AirportForm } from './airport-form';

describe('AirportForm', () => {
  let component: AirportForm;
  let fixture: ComponentFixture<AirportForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AirportForm]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AirportForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
