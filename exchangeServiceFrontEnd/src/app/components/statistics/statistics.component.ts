import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  roles: string[]
  response: StatsResponse;
  request: StatsRequest;

  constructor(
    private httpClient: HttpClient,
    private authService: AuthService) {
    this.roles = [];
  }

  getRoles(): string[] {
    if (this.roles.length === 0) this.authService.getRoles().subscribe(d => this.roles = d);
    return this.roles;
  }

  ngOnInit(): void {
    if (this.roles.indexOf("ADMIN") === -1) {


    }

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
