import { AccountService } from './../services/account.service';
import { AuthentificationService } from './../services/authentification.service';
import { Client } from './../class/client';

import { OperationService } from './../services/operation.service';
import { Component, OnInit } from '@angular/core';
import { Account } from '../class/account';

@Component({
  selector: 'app-print-page',
  templateUrl: './print-page.component.html',
  styleUrls: ['./print-page.component.css'],
})
export class PrintPageComponent implements OnInit {
  public operation: any;
  public display: boolean = false;
  public client: Client = new Client();
  public account:Account = new Account();

  constructor(
    private service: OperationService,
    private auth: AuthentificationService,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.onGetAccountClient();
  }



  onGetAccountClient() {
    this.client = this.auth.loadInfoClient();
    this.accountService.getAccount(this.client.id).subscribe(
      (data) => {
        this.account = data;
      },
      (err) => {
        //traitement d'erreur
      },()=>{
        this.getAllOperation(this.account.accountNumber);
        console.log(this.account.accountNumber + "Accc")
      }
    );
  }

  public getAllOperation(accountNumber: string) {
    this.service.getAllOperationClient(accountNumber).subscribe(
      (data) => {
        this.operation = data;
        console.log(data + "data")
        console.log(this.operation + "Operation")
      },
      (err) => {
        console.log(err);
      }
    );
  }

  onprintHistoriqueCompte(cmpName) {
    this.display = false;
    let printContents = document.getElementById(cmpName).innerHTML;
    let originalContents = document.body.innerHTML;
    document.body.innerHTML = printContents;
    window.print();
    document.body.innerHTML = originalContents;
  }
}
