
import { OperationService } from './../services/operation.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-print-page',
  templateUrl: './print-page.component.html',
  styleUrls: ['./print-page.component.css'],
})
export class PrintPageComponent implements OnInit {
  constructor(private service: OperationService) {}
  public operation: any;
  public display:boolean=false;

  ngOnInit(): void {
    this.getAllOperation();
  }

  public getAllOperation() {
    this.service.getAllOperationClient().subscribe(
      (data) => {
        this.operation = data;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  

  onprintHistoriqueCompte(cmpName) {
    this.display=false
    let printContents = document.getElementById(cmpName).innerHTML;
    let originalContents = document.body.innerHTML;
    document.body.innerHTML = printContents;
    window.print();
    document.body.innerHTML = originalContents;
}
}
