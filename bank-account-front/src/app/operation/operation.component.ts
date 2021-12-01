import { Client } from 'src/app/class/client';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { OperationService } from './../services/operation.service';
import { AuthentificationService } from './../services/authentification.service';
import { Expression } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { BrokerService } from '../broker/broker.service';
import { ActionsTypes } from '../state/state';
import { Operation } from '../class/operation';
import { Router } from '@angular/router';

@Component({
  selector: 'app-operation',
  templateUrl: './operation.component.html',
  styleUrls: ['./operation.component.css']
})
export class OperationComponent implements OnInit {
  public opertationForm: FormGroup;
  public client: Client;
  public operation:any;
  displayErrorMessage: boolean = false;
  typeOperation: string="CREDIT";

  constructor(private fb: FormBuilder, private auth: AuthentificationService,
    private service: OperationService, private broker: BrokerService,private router:Router ) { }

  ngOnInit(): void {
    this.opertationForm = this.fb.group({
      type: ['', [Validators.required]],
      amountOperation: ['', [Validators.required, Validators.minLength(1), Validators.maxLength(10)]],
      accountNumber: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(10)]],
      description:['', [Validators.required, Validators.maxLength(40)]],
      beneficiary: '',
    });
    this.getAllOperation();
  }



  public saveData() {
    if (this.opertationForm?.invalid) return;
    this.service.save(this.opertationForm?.value)
      .subscribe(data => {
        this.broker.publishEvent({ type: ActionsTypes.ADD_CLIENT });
      },err => {
        console.log( ' AuthentificationService KO   ' + err.message);
      })
  }


  public getAllOperation(){
    this.service.getAllOperationClient().subscribe(data => {
       this.operation = data;
        this.broker.publishEvent({ type: ActionsTypes.GET_ALL_CLIENTS});
        data.forEach(console.log)
      },err => {
        console.log(err);
      })
  }


  reloadCurrentPage() {
    window.location.reload();
   }

}
