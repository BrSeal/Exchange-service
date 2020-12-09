import {Component, OnInit, Output} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {StatsService} from "../../services/stats.service";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  roles: string[]
  request: StatsRequest;
  usernames: string[]
  total = 0;
  ones =0;
  label: string;

  constructor(
    private statsService: StatsService,
    private authService: AuthService) {
    this.roles = [];
  }

  getRoles(): string[] {
    return this.roles;
  }

  ngOnInit(): void {
    this.authService.getRoles().subscribe(d => this.roles = d);
  }

  showMoreThanInTotal() {
    this.statsService.getStats({moreThanInTotal: this.total,moreThanAtOnes:0})
      .subscribe(data => {
        this.label="Show users who exchanged more than "+this.total+" in total";
        this.usernames = data.exchangedMany;
      });
  }

  showMoreThanAtOnes() {
    this.statsService.getStats({moreThanAtOnes: this.ones,moreThanInTotal:0})
      .subscribe(data => {
        this.label="Show users who exchanged more than "+this.ones+" at ones";
        this.usernames = data.moreThanAtOnes;
      });

  }
}

export interface StatsResponse {
  myExchanges: Exchange[];
  exchangedMany: string[];
  moreThanAtOnes: string[];
}

export interface StatsRequest {
  moreThanInTotal: number;
  moreThanAtOnes: number;
}

export interface Exchange {
  id: number;
  user: string;
  from: string;
  to: string;
  operationDate: Date
  amount: number;
  resultingAmount: number;
}
