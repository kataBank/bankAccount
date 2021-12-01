import { AccountService } from './../services/account.service';
import { AuthentificationService } from './../services/authentification.service';
import { Account } from './../class/account';
import { Client } from './../class/client';

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public client:Client = new Client();
  public account: Account = new Account();

  constructor(private auth:AuthentificationService, private accountService:AccountService) { }

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
      }
    );
  }

  onprintInfoCompte(cmpName) {

    let printContents = document.getElementById(cmpName).innerHTML;
    let originalContents = document.body.innerHTML;
    document.body.innerHTML = printContents;
    window.print();
    document.body.innerHTML = originalContents;
}

}
