import { Component, OnInit, Input } from '@angular/core';
import { Client } from 'src/app/class/client';

@Component({
  selector: 'app-client-item',
  templateUrl: './client-item.component.html',
  styleUrls: ['./client-item.component.css']
})
export class ClientItemComponent implements OnInit {

  @Input() client: Client | null = null;

  constructor() { }

  ngOnInit(): void {
  }

  onEdit(client: Client) {

  }
  onSelect(client: Client) {

  }
}
