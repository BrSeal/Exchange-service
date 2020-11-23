package exchangeApp.exchangeAndStats.service;

import exchangeApp.exchangeAndStats.entity.Exchange;

import java.util.List;

public interface StatsService {

    List<Exchange> getAll();

    Exchange getById(int id);

    List<Exchange> findAllByUsername(String username);

    List<String> getUsersChangedMoreThan(double amount,String base);

    List<String> getUsersTotalExchangedMoreThan(double amount,String base);

    List<String> getExchangeRating();
}
