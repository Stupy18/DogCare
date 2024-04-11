import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlannedActivitiesButtonComponent } from './planned-activities-button.component';

describe('PlannedActivitiesButtonComponent', () => {
  let component: PlannedActivitiesButtonComponent;
  let fixture: ComponentFixture<PlannedActivitiesButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PlannedActivitiesButtonComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PlannedActivitiesButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
