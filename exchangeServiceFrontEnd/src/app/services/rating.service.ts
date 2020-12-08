import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RatingService {
  private ratingUrl = 'http://localhost:8080/rating';

  constructor(private httpClient:HttpClient,
              private authService:AuthService) { }

  getRatingList():Observable<Rating[]>{
    return  this.httpClient.get<Rating[]>(this.ratingUrl, this.authService.getHeaders());
  }
}

export interface Rating {
  frequency:number;
  from:string;
  to:string;
}
