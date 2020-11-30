import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ExchangeRatesComponent } from './components/exchange-rates/exchange-rates.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import {HttpClientModule} from "@angular/common/http";
import {ExchangeRatesService} from "./services/exchange-rates.service";

@NgModule({
  declarations: [
    AppComponent,
    ExchangeRatesComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [ExchangeRatesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
