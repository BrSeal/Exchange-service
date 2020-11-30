import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AuthService} from "./auth.service";

@Injectable({
  providedIn: 'root'
})
export class ExchangeRatesService {

  private baseUrl = 'http://localhost:8080/exchange/rates';

  constructor(private httpClient:HttpClient,
              private authService:AuthService) { }

  getExchangeRatesList():Observable<MapWithBase>{
   return  this.httpClient.get<MapWithBase>(this.baseUrl, this.authService.getHeaders());
  }
}

interface MapWithBase {
  base:string,
  map:Map<string, number>
}
