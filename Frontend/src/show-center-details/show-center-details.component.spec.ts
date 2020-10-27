import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowCenterDetailsComponent } from './show-center-details.component';

describe('ShowCenterDetailsComponent', () => {
  let component: ShowCenterDetailsComponent;
  let fixture: ComponentFixture<ShowCenterDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowCenterDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowCenterDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
