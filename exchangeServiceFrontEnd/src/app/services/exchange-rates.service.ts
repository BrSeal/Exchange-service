import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ExchangeRatesService {

  private baseUrl = 'http://localhost:8080/exchange/rates';

  constructor(private httpClient:HttpClient) { }

  getExchangeRatesList():Observable<Map<string, number>>{
   return  this.httpClient.get<Map<string, number>>(this.baseUrl);
  }
}
