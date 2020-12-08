import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {ExchangeRatesComponent} from "./components/exchange-rates/exchange-rates.component";
import {LoginComponent} from "./components/login/login.component";
import {StatisticsComponent} from "./components/statistics/statistics.component";
import {GuardGuard} from "./auth/guard.guard";
import {RegistrationComponent} from "./components/registration/registration.component";
import {RatingComponent} from "./components/rating/rating.component";
import {ExchangeTableComponent} from "./components/statistics/exchange-table/exchange-table.component";

const routes: Routes = [
  {path: '', component: ExchangeRatesComponent},
  {path: 'getRates', component: ExchangeRatesComponent},
  {path: 'getStats', component: StatisticsComponent, canActivate: [GuardGuard]},
  {path: 'loginForm', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'getRatings', component: RatingComponent},
  {path: 'myExchanges', component: ExchangeTableComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers: [GuardGuard],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
