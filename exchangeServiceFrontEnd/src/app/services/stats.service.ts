import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {StatsRequest, StatsResponse} from "../components/statistics/statistics.component";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StatsService {

  private statsUrl = "http://localhost:8080/stats"

  constructor(private httpClient: HttpClient,
              private authService: AuthService) {
  }

  getStats(request?: StatsRequest): Observable<StatsResponse> {
    let params = {
      ...this.authService.getHeaders(),
      params: request||{}
    }
    // @ts-ignore
    return this.httpClient.get<StatsResponse>(this.statsUrl, params);
  }
}

