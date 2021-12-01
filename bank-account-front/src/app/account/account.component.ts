import { BrokerService } from './../broker/broker.service';
import { Client } from './../class/client';
import { AuthentificationService } from './../services/authentification.service';
import { AccountService } from './../services/account.service';
import { Component, OnInit } from '@angular/core';
import { Account } from '../class/account';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
})
export class AccountComponent implements OnInit {
  public accountForm: FormGroup;
  public client: Client = new Client();
  public account: Account = new Account();
  public accountList: Account[];

  constructor(
    private fb: FormBuilder,
    private auth: AuthentificationService,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.onGetInfoClient() ;
    this.onGetAll();
    this.accountForm = this.fb.group({
      accountNumber: [
        '',
        [
          Validators.required,
          Validators.maxLength(10),
        ],
      ],
    });

  }

  onGetInfoClient() {
    this.client = this.auth.loadInfoClient();
  }

  onGetAll() {
    this.accountService.getAllAccount().subscribe(
      (data) => {
        this.accountList = data;
        console.log(data);
      },
      (err) => {
        //traitement d'erreur
      }
    );
  }

  public saveData() {
    console.log(this.accountForm.value)
    if (this.accountForm?.invalid) return;
    this.accountService.save(this.accountForm?.value).subscribe((data) => {
      console.log(data);
    },
      (err) => {
        //Traitement d'erreur
      }
    );
  }

  reloadCurrentPage() {
    window.location.reload();
   }
}
