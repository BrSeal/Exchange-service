import {Component, OnInit} from '@angular/core';
import {ExchangeRatesService} from "../../services/exchange-rates.service";
import {ExchangeRate} from "../../common/exchange-rate";

@Component({
  selector: 'app-exchange-rates',
  templateUrl: './exchange-rates.component.html',
  styleUrls: ['./exchange-rates.component.css']
})
export class ExchangeRatesComponent implements OnInit {

  rates: ExchangeRate[];

  constructor(private ratesService: ExchangeRatesService) {
    this.rates = [];
  }

  ngOnInit(): void {
    this.listRates();

  }

  listRates() {
    this.ratesService.getExchangeRatesList().subscribe(data => {
      Object.keys(data).forEach(k => this.rates.push(new ExchangeRate(k, data[k])))
    });
  }
}
