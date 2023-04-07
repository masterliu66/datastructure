package com.example.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * className AuthenticationManager
 * packageName com.example.leetcode
 * description
 *
 * @author CCC
 * @date 2021/4/27 23:23
 */
public class AuthenticationManager {

    int timeToLive;

    Map<String, Integer> map;

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;
        this.map = new HashMap<>();
    }

    public void generate(String tokenId, int currentTime) {

        map.put(tokenId, currentTime);
    }

    public void renew(String tokenId, int currentTime) {

        checkExpiredTokenId(currentTime);

        Integer time = map.get(tokenId);
        if (time == null) {
            return;
        }

        map.put(tokenId, currentTime);
    }

    public int countUnexpiredTokens(int currentTime) {

        checkExpiredTokenId(currentTime);

        return map.size();
    }

    private void checkExpiredTokenId(int currentTime) {

        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            Integer time = entry.getValue();
            if (currentTime - time >= timeToLive) {
                iterator.remove();
            }
        }

    }

}
