import { Component, OnInit, Input } from '@angular/core';
import { FormGroup, FormBuilder, Validator, Validators } from '@angular/forms';
import { Client } from '../class/client';
import { ClientService } from '../services/client.service';
import { BrokerService } from '../broker/broker.service';
import { ActionsTypes, AppDataState, DataStateEnum, ActionEvent } from '../state/state';
import { Observable, of } from 'rxjs';
import { map, catchError, startWith } from 'rxjs/operators';

@Component({
  selector: 'app-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit {

  public clientForm: FormGroup;
  public client: Client;
  clientIn$:Observable<AppDataState<Client[]>> |null=null;


  constructor(private fb: FormBuilder, private service: ClientService, private broker: BrokerService) { }

  ngOnInit(): void {
    this.onGetAllClients();
    this.broker.publishEvent({type: ActionsTypes.GET_ALL_CLIENTS});
    this.broker.sourceEventSubjectObservable.subscribe((actionEvent: ActionEvent) => {
      this.onActionEvent(actionEvent);
    });

    this.clientForm = this.fb.group({
      firstName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(10)]],
      lastName: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(10)]],
      emailAdress: ['', [Validators.required, Validators.email]],
      birtDayDate: '',
      phoneNumber: '',
    });


  }
  onActionEvent($event: ActionEvent) {
    switch ($event.type) {
      case ActionsTypes.GET_ALL_CLIENTS: this.onGetAllClients();break;
    }
  }
  onGetAllClients() {
    this.clientIn$=this.service.getAllClient().pipe(
      map(data=>{
        console.log(data);
        return ({dataState:DataStateEnum.LOADED,data:data})
      }),
      startWith({dataState:DataStateEnum.LOADING}),
      catchError(err=>of({dataState:DataStateEnum.ERROR, errorMessage:err.message}))
    );
  }

  public saveData() {
    if (this.clientForm?.invalid) return;
    this.service.save(this.clientForm?.value)
      .subscribe(data => {
        this.onGetAllClients;
        this.broker.publishEvent({ type: ActionsTypes.ADD_CLIENT });
      });
      this.onGetAllClients;
  }

  reloadCurrentPage() {
    window.location.reload();
   }
}


