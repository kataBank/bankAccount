import { Observable } from 'rxjs';
import { Beneficiary } from './../class/beneficiary';
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BeneficiaryService {

  host: string;
  context_path: string;
  constructor(private http: HttpClient) {
    this.host = environment.host;
    this.context_path = environment.context_path;
  }


  getAllBeneficiaryClient(): Observable<Beneficiary[]> {
    return this.http.get<Beneficiary[]>(this.host + this.context_path + "/beneficiary");
  }
  save(beneficiary: Beneficiary): Observable<Beneficiary> {
    return this.http.post<Beneficiary>(this.host + this.context_path + "/beneficiary", beneficiary);
  }
  getBeneficiaryById(id: number): Observable<Beneficiary> {
    return this.http.get<Beneficiary>(this.host + this.context_path + "/beneficiary/" + id);
  }
}
