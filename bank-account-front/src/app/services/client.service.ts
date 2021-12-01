import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Client } from '../class/client';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  host: string;
  context_path: string;
  constructor(private http: HttpClient) {
    this.host = environment.host;
    this.context_path = environment.context_path;
  }


  getAllClient(): Observable<Client[]> {
    return this.http.get<Client[]>(this.host + this.context_path + "/client/all");
  }
  save(client: Client): Observable<Client> {
    return this.http.post<Client>(this.host + this.context_path + "/client", client);
  }
  getClient(id: number): Observable<Client> {
    return this.http.get<Client>(this.host + this.context_path + "/client/" + id);
  }

}
