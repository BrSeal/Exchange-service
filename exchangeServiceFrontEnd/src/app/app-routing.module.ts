import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ExchangeRatesComponent} from "./components/exchange-rates/exchange-rates.component";
import {LoginComponent} from "./components/login/login.component";
import {StatisticsComponent} from "./components/statistics/statistics.component";
import {GuardGuard} from "./auth/guard.guard";

const routes: Routes = [
  {path: 'getRates', component: ExchangeRatesComponent},
  {path: 'getStats', component: StatisticsComponent, canActivate: [GuardGuard]},
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers: [GuardGuard],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
