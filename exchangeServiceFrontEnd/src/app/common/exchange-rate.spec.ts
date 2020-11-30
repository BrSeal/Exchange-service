import { ExchangeRate } from './exchange-rate';

describe('ExchangeRate', () => {
  it('should create an instance', () => {
    let base:string = "USD";
    let rate:number = 1;
    expect(new ExchangeRate(base,rate)).toBeTruthy();
  });
});
