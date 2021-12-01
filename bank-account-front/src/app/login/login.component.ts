import { AccountService } from './../services/account.service';
import { Account } from './../class/account';
import { BrokerService } from './../broker/broker.service';
import { ClientService } from './../services/client.service';
import { Client } from './../class/client';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { AuthentificationService } from '../services/authentification.service';
import { Router } from '@angular/router';
import { ActionsTypes } from '../state/state';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public loginForm: FormGroup;
  public isAuth: boolean = true;
  private account: Account = new Account();

  constructor(
    private authServ: AuthentificationService,
    private router: Router,
    private fb: FormBuilder,
    private service: ClientService,
    private accountService: AccountService,
    private broker :BrokerService
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      accountNumber: ['', [Validators.required, Validators.maxLength(10)]],
      clientId: ['', [Validators.required, Validators.maxLength(10)]],
    });
  }

  onLogin() {
    if (this.loginForm?.invalid) return;
    this.authServ.onLogin(this.loginForm?.value).subscribe(
      (resp) => {
        this.authServ.saveToken(resp.body);
        this.isAuth = false;
        this.router.navigate(['/operation']);
      },
      (err) => {
        //Traitement des erreurs
        //Acitve war message
      },
      () => {
        //Recupération des infos du compte si l'authentification es ok
      //  this.onGetAccountClient();
      }
    );
  }

}
