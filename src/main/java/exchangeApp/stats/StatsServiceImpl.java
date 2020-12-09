package exchangeApp.stats;

import exchangeApp.exchange.ExchangeRepository;
import exchangeApp.security.UserRepository;
import exchangeApp.security.entity.User;
import exchangeApp.stats.DTO.ExchangeDto;
import exchangeApp.stats.DTO.StatsRequest;
import exchangeApp.stats.DTO.StatsResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final ExchangeRepository exchangeRepository;
    private final UserRepository userRepository;

    @Override
    public StatsResponse getStats(String username, StatsRequest request, List<String> authorities) {
        StatsResponse response = new StatsResponse();
        User user=userRepository.findById(username).orElseThrow(()->new IllegalArgumentException("User with name \""+username+"\" not found!"));

        if (authorities.contains("ROLE_ADMIN") && request != null) {
            double moreThanInTotal = request.getMoreThanInTotal();
            double moreThanAtOnes = request.getMoreThanAtOnes();

            List<ExchangeDto> exchangeDtos=exchangeRepository.findAllByUser(user).stream()
                        .map(ExchangeDto::new)
                        .collect(Collectors.toList());

                response.setMyExchanges(exchangeDtos);

            if (moreThanInTotal != 0) {
                List<String> usernames = userRepository.getUsersExchangedMoreInTotal(moreThanInTotal)
                        .stream().map(User::getUsername)
                        .collect(Collectors.toList());
                response.setExchangedMany(usernames);
            }

            if (moreThanAtOnes != 0) {
                List<String> usernames = userRepository.getExchangedMoreThanAtOnes(moreThanAtOnes)
                        .stream().map(User::getUsername)
                        .collect(Collectors.toList());
                response.setMoreThanAtOnes(usernames);
            }
        }
        else {
            List<ExchangeDto> exchangeDtos=exchangeRepository.findAllByUser(user).stream()
                    .map(ExchangeDto::new)
                    .collect(Collectors.toList());

            response.setMyExchanges(exchangeDtos);
        }

        return response;
    }
}
