import { Injectable } from '@angular/core';
import { JwtHelperService } from "@auth0/angular-jwt"
import { HttpClient } from '@angular/common/http';
import { Client } from '../class/client';
import { environment } from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {

 jwt: string;
 client: Client;
 host:string;

  constructor(private http: HttpClient) {
    this.host = environment.auth;
  }


  onLogin(data) {
   return this.http.post(this.host, data, { observe: 'response' });
  }

  saveToken(token: any) {
    localStorage .setItem('token', token);
  }


   loadInfoClient(): Client {
    const jwtHelper = new JwtHelperService();
    this.client = new Client;
     // decode JWT
    const obJwt = jwtHelper.decodeToken(localStorage.getItem('token'));

    this.client.id = obJwt.id;
    this.client.accountNumber = obJwt.accountNumber;
    this.client.firstName = obJwt.firstName;
    this.client.lastName = obJwt.lastName;
    this.client.phoneNumber = obJwt.phoneNumber;
    this.client.clientId = obJwt.clientId;
    this.client.emailAdress = obJwt.emailAdress;
    this.client.birtDayDate = obJwt.birtDayDate;
    this.client.clientId = obJwt.clientId;
    this.client.role = obJwt.role;

    return this.client;
  }

  isAuthentication() {
    if (localStorage.getItem('token') != null) {
      return true;
    }
    return false ;
  }

  deconexion() {
    localStorage.removeItem('token');
  }
}
