import {Component, Input, OnInit} from '@angular/core';
import {Exchange, StatsResponse} from "../statistics.component";
import {ResourcesService} from "../../../services/resources.service";
import {StatsService} from "../../../services/stats.service";

@Component({
  selector: 'app-exchange-table',
  templateUrl: './exchange-table.component.html',
  styleUrls: ['./exchange-table.component.css']
})
export class ExchangeTableComponent implements OnInit {

  exchanges:Exchange[];

  constructor(private res:ResourcesService,
              private statsService:StatsService) {
  }

  ngOnInit(): void {
    this.statsService.getStats().subscribe(data=>this.exchanges=data.myExchanges);
  }
}
