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
 @Output() response: StatsResponse;
  request: StatsRequest;


  constructor(
    private statsService:StatsService,
    private authService: AuthService) {
    this.roles = [];
  }

  getRoles(): string[] {
    if (this.roles.length === 0) this.authService.getRoles().subscribe(d => this.roles = d);
    return this.roles;
  }

  ngOnInit(): void {
    this.getRoles();
    if(this.getRoles().indexOf('ADMIN')===-1) this.getStats();
  }

  getStats(){
   this.statsService.getStats(this.request).subscribe(response=>{
     this.response=response;
     alert(response.exchanges[0]+" "+this.response.exchanges[0]);
   });
  }
}

export interface StatsResponse {
  exchanges: Exchange[];
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
