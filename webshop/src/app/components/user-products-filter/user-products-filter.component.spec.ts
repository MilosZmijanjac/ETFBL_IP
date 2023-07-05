import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProductsFilterComponent } from './user-products-filter.component';

describe('UserProductsFilterComponent', () => {
  let component: UserProductsFilterComponent;
  let fixture: ComponentFixture<UserProductsFilterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserProductsFilterComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserProductsFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
