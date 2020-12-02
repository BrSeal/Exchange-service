import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ExchangeRatesComponent } from './components/exchange-rates/exchange-rates.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import {HttpClientModule} from "@angular/common/http";
import {ExchangeRatesService} from "./services/exchange-rates.service";
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './components/login/login.component';
import { StatisticsComponent } from './components/statistics/statistics.component';
import {FormsModule} from "@angular/forms";
import { RatesFormWithBaseComponent } from './rates-form-with-base/rates-form-with-base.component';

@NgModule({
  declarations: [
    AppComponent,
    ExchangeRatesComponent,
    NavbarComponent,
    LoginComponent,
    StatisticsComponent,
    RatesFormWithBaseComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [ExchangeRatesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
