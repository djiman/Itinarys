import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TrajettableComponent } from './trajettable.component';

describe('TrajettableComponent', () => {
  let component: TrajettableComponent;
  let fixture: ComponentFixture<TrajettableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TrajettableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TrajettableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
