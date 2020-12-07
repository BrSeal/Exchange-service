import {Component, OnInit} from '@angular/core';
import {ExchangeRatesService} from "../../services/exchange-rates.service";
import {ExchangeRate} from "../../common/exchange-rate";
import {AuthService} from "../../services/auth.service";
import {ExchangeService} from "../../services/exchange.service";

@Component({
  selector: 'app-exchange-rates',
  templateUrl: './exchange-rates.component.html',
  styleUrls: ['./exchange-rates.component.css']
})
export class ExchangeRatesComponent implements OnInit {
  rates: ExchangeRate[];
  currentBase: string;
  to: string;
  amount: number;

  constructor(private ratesService: ExchangeRatesService,
              private exchangeService:ExchangeService,
              private authService: AuthService) {
    this.currentBase = "usd"
    this.rates = [];
  }

  ngOnInit(): void {
    this.listRates();
  }

  listRates() {
    this.ratesService.getExchangeRatesList(this.currentBase).subscribe(
      data => {
        this.currentBase = data.base;
        let rates = [];
        Object.keys(data.rates).forEach(k => rates.push(new ExchangeRate(k, data.rates[k])));
        this.rates=rates.sort((a,b)=>a.base<b.base?-1:1)
      },
      error => alert(error.message)
    );
  }

  isAuth() {
    return this.authService.isAuth();
  }

  doExchange() {
    this.exchangeService.doExchange({amount:this.amount,from:this.currentBase,to:this.to})
      .subscribe(data=>alert("Exchange id: "+data.exchangeId+"\nResulting amount: "+data.amount),
        error => alert("Something gone horribly wrong!!!"))
  }
}
