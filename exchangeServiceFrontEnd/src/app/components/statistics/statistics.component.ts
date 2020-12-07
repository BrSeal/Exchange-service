import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {
  public roles:string[]

  constructor(
    private authService:AuthService) {
    this.roles=[];
  }

  getRoles():string[]{
    if(this.roles.length===0) this.authService.getRoles().subscribe(d=>this.roles=d);
    return this.roles;
  }

  ngOnInit(): void {
  }

}
