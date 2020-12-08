import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {StatsRequest, StatsResponse} from "../components/statistics/statistics.component";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class StatsService {

  private statsUrl="http://localhost:8080/stats"

  constructor(private httpClient: HttpClient,
              private authService: AuthService) {
  }

  getStats(request: StatsRequest):Observable<StatsResponse> {
    if(this.authService.roles.indexOf("ADMIN")===-1)
      return  this.httpClient.get<StatsResponse>(this.statsUrl, this.authService.getHeaders());
    return  this.httpClient.post<StatsResponse>(this.statsUrl, request, this.authService.getHeaders());
  }
}

