package exchangeApp.stats;

import exchangeApp.stats.DTO.StatsRequest;
import exchangeApp.stats.DTO.StatsResponse;

import java.util.List;

public interface StatsService {
    StatsResponse getStats(String username, StatsRequest request, List<String> authorities);
}
