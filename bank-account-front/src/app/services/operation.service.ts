import { Account } from './../class/account';

import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { Operation } from '../class/operation';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OperationService {

  host: string;
  context_path: string;

  constructor(private http: HttpClient) {
    this.host = environment.host;
    this.context_path = environment.context_path;
  }

  getAllOperationClient(accountNumber:string): Observable<Operation[]> {
    return this.http.get<Operation[]>(this.host + this.context_path + "/operation" + accountNumber);
  }
  save(operation: Operation): Observable<Operation> {
    return this.http.post<Operation>(this.host + this.context_path + "/operation", operation);
  }
  getOperation(id: number): Observable<Operation> {
    return this.http.get<Operation>(this.host + this.context_path + "/operation/" + id);
  }
}
