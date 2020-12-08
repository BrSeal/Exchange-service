import { Injectable } from '@angular/core';
import {Exchange} from "../components/statistics/statistics.component";

@Injectable({
  providedIn: 'root'
})
export class ResourcesService {

  exchanges:Exchange[];
  exchangedMany: string[];
  moreThanAtOnes: string[];
  moreThan:number;
  total:number;
  myExchanges:boolean;

  constructor() {
  }

  setDefault():void{
    this.exchanges=[];
    this.exchangedMany=[];
    this.moreThanAtOnes=[];
    this.moreThan=0;
    this.total=0;
    this.myExchanges=true;
  }
}
