import {Component, Input, OnInit} from '@angular/core';
import {Exchange, StatsResponse} from "../statistics.component";
import {ResourcesService} from "../../../services/resources.service";

@Component({
  selector: 'app-exchange-table',
  templateUrl: './exchange-table.component.html',
  styleUrls: ['./exchange-table.component.css']
})
export class ExchangeTableComponent implements OnInit {

  exchanges:Exchange[];

  constructor(private res:ResourcesService) {
    this.exchanges=this.res.exchanges;
  }

  ngOnInit(): void {
  }
}
