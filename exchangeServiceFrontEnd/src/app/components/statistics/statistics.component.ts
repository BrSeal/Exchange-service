import {Component, OnInit, Output} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {StatsService} from "../../services/stats.service";
import {ResourcesService} from "../../services/resources.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  roles: string[]
  @Output() response: StatsResponse;
  request: StatsRequest;


  constructor(
    private statsService: StatsService,
    private authService: AuthService,
    private res: ResourcesService,
    private router: Router) {
    this.roles = [];
  }

  getRoles(): string[] {
    return this.roles;
  }

  ngOnInit(): void {
    this.authService.getRoles().subscribe(d => this.roles = d);
  }

  getStats() {
    this.statsService.getStats(this.request).subscribe(response => {
      this.response = response;
      this.res.exchanges = response.myExchanges;
      this.res.moreThanAtOnes = response.moreThanAtOnes;
      this.res.exchangedMany = response.exchangedMany;
    });
    if (this.getRoles().indexOf("ROLE_ADMIN") === -1) this.router.navigate(['/myExchanges']);
  }

  showMyExchanges() {
    this.statsService.getStats(this.request).subscribe(response => this.res.exchanges = response.myExchanges);
    this.router.navigate(['/myExchanges']);
  }

  showMoreThanInTotal() {

  }

  showMoreThanAtOnes() {

  }
}

export interface StatsResponse {
  myExchanges: Exchange[];
  exchangedMany: string[];
  moreThanAtOnes: string[];
}

export interface StatsRequest {
  myExchanges: boolean;
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
