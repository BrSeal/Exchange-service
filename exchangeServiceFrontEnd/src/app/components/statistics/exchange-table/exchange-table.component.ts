import {Component, Input, OnInit} from '@angular/core';
import {Exchange} from "../statistics.component";

@Component({
  selector: 'app-exchange-table',
  templateUrl: './exchange-table.component.html',
  styleUrls: ['./exchange-table.component.css']
})
export class ExchangeTableComponent implements OnInit {

  @Input() exchanges:Exchange[];

  constructor() { }

  ngOnInit(): void {
  }

}
