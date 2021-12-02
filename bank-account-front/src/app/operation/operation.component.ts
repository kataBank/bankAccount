import { AccountService } from './../services/account.service';
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
import { Account } from '../class/account';

@Component({
  selector: 'app-operation',
  templateUrl: './operation.component.html',
  styleUrls: ['./operation.component.css'],
})
export class OperationComponent implements OnInit {
  public opertationForm: FormGroup;
  public client: Client = new Client();
  public operation: any;
  public account: Account = new Account();
  displayErrorMessage: boolean = false;
  typeOperation: string = 'CREDIT';

  constructor(
    private fb: FormBuilder,
    private auth: AuthentificationService,
    private service: OperationService,
    private broker: BrokerService,
    private router: Router,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {

    this.onGetAccountClient();

    this.opertationForm = this.fb.group({
      type: ['', [Validators.required]],
      amountOperation: [
        '',
        [
          Validators.required,
          Validators.minLength(1),
          Validators.maxLength(10),
        ],
      ],
      accountNumber: [
        '',
        [
          Validators.required,
          Validators.minLength(5),
          Validators.maxLength(10),
        ],
      ],
      description: ['', [Validators.required, Validators.maxLength(40)]],
      beneficiary: '',
    });
  }

  public saveData() {
    if (this.opertationForm?.invalid) return;
    this.service.save(this.opertationForm?.value).subscribe(
      (data) => {
        this.broker.publishEvent({ type: ActionsTypes.ADD_CLIENT });
      },
      (err) => {
        console.log(' AuthentificationService KO   ' + err.message);
      }
    );
  }

  onGetAccountClient() {
    this.client = this.auth.loadInfoClient();
    this.accountService.getAccount(this.client.id).subscribe(
      (data) => {
        this.account = data;
      },
      (err) => {
        //traitement d'erreur
      },
      () => {
        this.getAllOperationClient(this.account.accountNumber);
      }
    );
  }

  public getAllOperationClient(accountNumber) {
    this.service.getAllOperationClient(accountNumber).subscribe(
      (data) => {
        this.operation = data;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  reloadCurrentPage() {
    window.location.reload();
  }
}
