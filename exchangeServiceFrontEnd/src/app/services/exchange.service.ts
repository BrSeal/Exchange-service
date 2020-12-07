import {Injectable} from '@angular/core';
import {AuthService} from "./auth.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ExchangeService {

  private exchangeUrl = "'http://localhost:8080/exchange";

  constructor(private httpClient: HttpClient,
              private authService: AuthService,) {
  }

  doExchange({amount, from, to}): Observable<ExchangeResult> {
    return this.httpClient.post<ExchangeResult>(
      this.exchangeUrl,
      {from, to, amount},
      this.authService.getHeaders()
    );
  }
}

export interface ExchangeResult {
  id: number,
  amount: number
}
