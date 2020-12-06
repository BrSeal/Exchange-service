package exchangeApp.security.jwt;


import java.util.HashSet;

public final class JwtTokenBlackList {
    private static HashSet<String> blackList;

    public synchronized static boolean isInBlackList(String token){
        return blackList != null && blackList.contains(token);
    }

    public synchronized static void addToBlackList(String token){
        if(blackList==null)blackList=new HashSet<>();
        blackList.add(token);
    }
}
