import {Component, OnInit} from '@angular/core';
import {ExchangeRatesComponent} from "../components/exchange-rates/exchange-rates.component";

@Component({
  selector: 'app-rates-form-with-base',
  templateUrl: './rates-form-with-base.component.html',
  styleUrls: ['./rates-form-with-base.component.css']
})
export class RatesFormWithBaseComponent implements OnInit {
  public base: string;
  public currencyNames: string[];
  constructor(
    private exchangeRates: ExchangeRatesComponent) {}

  ngOnInit(): void {
    this.exchangeRates.rates.map(value=> this.currencyNames.push(value.base));
   this.currencyNames.sort();
  }


  getRatesWithBase() {
    this.exchangeRates.setCurrentBase(this.base);
  }
}
