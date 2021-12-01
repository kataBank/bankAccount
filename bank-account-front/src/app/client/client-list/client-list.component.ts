import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { AppDataState, DataStateEnum } from 'src/app/state/state';
import { Client } from 'src/app/class/client';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
  @Input() clientsInput$:Observable<AppDataState<Client[]>>;
  readonly DataStateEnum=DataStateEnum;

  constructor() { }

  ngOnInit(): void {
    console.log("List " + this.clientsInput$ );
  }

}
