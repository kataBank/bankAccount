import { AuthentificationService } from './../services/authentification.service';
import { Component, OnInit } from '@angular/core';
import { Client } from '../class/client';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  public client: Client;

  constructor(private auth:AuthentificationService) { }

  ngOnInit(): void {
    this.client=this.auth.loadInfoClient();
  }

  public deconexion(){
    this.auth.deconexion();
  }

}
