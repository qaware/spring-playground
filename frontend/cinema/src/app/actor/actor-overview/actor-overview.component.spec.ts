import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActorOverviewComponent } from './actor-overview.component';

describe('ActorOverviewComponent', () => {
  let component: ActorOverviewComponent;
  let fixture: ComponentFixture<ActorOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActorOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActorOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
