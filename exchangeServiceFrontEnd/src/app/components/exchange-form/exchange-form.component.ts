import {Component, OnInit} from '@angular/core';
import {ExchangeResult, ExchangeService} from "../../services/exchange.service";

@Component({
  selector: 'app-exchange-form',
  templateUrl: './exchange-form.component.html',
  styleUrls: ['./exchange-form.component.css']
})
export class ExchangeFormComponent implements OnInit {
  from: string;
  to: string;
  amount: number;

  constructor(
    private exchangeService: ExchangeService,
  ) {}

  ngOnInit(): void {
  }

  doExchange() {
    let result: ExchangeResult;
    this.exchangeService.doExchange({from: this.from, to: this.to, amount: this.amount})
      .subscribe(data => result = data, error => console.log(error.message));

    alert("Operation id:" + result.id + "\n Exchanged amount: " + result.amount + " " + this.to);
  }
}
