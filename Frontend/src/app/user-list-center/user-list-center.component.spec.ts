import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserListCenterComponent } from './user-list-center.component';

describe('UserListCenterComponent', () => {
  let component: UserListCenterComponent;
  let fixture: ComponentFixture<UserListCenterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserListCenterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserListCenterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
