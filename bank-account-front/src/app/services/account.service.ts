import { Observable } from 'rxjs';
import { Account } from './../class/account';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  host: string;
  context_path: string;
  constructor(private http: HttpClient) {
    this.host = environment.host;
    this.context_path = environment.context_path;
  }


  getAllAccount(): Observable<Account[]> {
    return this.http.get<Account[]>(this.host + this.context_path + "/account");
  }
  save(account: Account): Observable<Account> {
    return this.http.post<Account>(this.host + this.context_path + "/account", account);
  }
  getAccount(idClient: number): Observable<Account> {
    return this.http.get<Account>(this.host + this.context_path + "/account/" + idClient);
  }
}
