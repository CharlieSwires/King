package com.charlie;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class KingTest {

    public KingTest() {
        
    }
    private static Map<Integer, HashMap<Integer, Integer>> levelUseridMap = new HashMap<Integer,HashMap<Integer,Integer>>();
    private static Map<Integer, Integer> levelScoreMap = new HashMap<Integer, Integer>();
    private static Map<String, Date> sessionKeyMap = new HashMap<String, Date>();
    private static Map<String, Integer> useridMap = new HashMap<String, Integer>();
    private static final Long SESSION_TIMEOUT = (long)10*60*1000;//10 mins
    private static final int MAX_LIST = 15;
    
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public synchronized String getSessionKey (Integer userid){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[40];
        random.nextBytes(bytes);
        String sessionKey = bytesToHex(bytes); 
        sessionKeyMap.put(sessionKey,new Date(new Date().getTime()+SESSION_TIMEOUT));
        useridMap.put(sessionKey,userid);
        return sessionKey;
    }
    public boolean isValidSession(String sessionKey) {
        if (!sessionKeyMap.containsKey(sessionKey)) return false;
        if (sessionKeyMap.get(sessionKey).getTime() > new Date().getTime()) return true;
        return false;
    }

    public Integer getUserid(String sessionkey) {
        return useridMap.get(sessionkey);
    }

    public synchronized void recordLevelAndScore(Integer userid, Integer levelid, Integer score) {
        if(levelScoreMap.get(levelid) == null ||levelScoreMap.get(levelid) == 0) {
            levelScoreMap.put(levelid, score);
            levelUseridMap.put(levelid, new HashMap<Integer,Integer>());
            (levelUseridMap.get(levelid)).put(score,userid);
        } else if (levelScoreMap.get(levelid) < score && levelUseridMap.get(levelid).containsValue(userid)) {
            levelScoreMap.put(levelid, score);

            for (Integer key : levelUseridMap.get(levelid).keySet()) {
                if (levelUseridMap.get(levelid).get(key).equals(userid)) {
                    levelUseridMap.get(levelid).remove(key);
                    break;
                }
            }
            (levelUseridMap.get(levelid)).put(score,userid);
        } else if (levelScoreMap.get(levelid) < score) {
            levelScoreMap.put(levelid, score);

            (levelUseridMap.get(levelid)).put(score,userid);

        }
    }

    public String getLevelScores(Integer levelid) {
        HashMap<Integer, Integer> scoreAndUid = levelUseridMap.get(levelid);
        if (scoreAndUid != null) {
            Object[] unsorted = scoreAndUid.keySet().toArray();
            List<Object> unsortedList = Arrays.asList(unsorted);
            Collections.sort(unsortedList, new Comparator<Object>(){

                @Override
                public int compare(java.lang.Object o1, java.lang.Object o2) {
                    Integer a = (Integer)o1;
                    Integer b = (Integer)o2;
                    if (a == null || b == null) return 0;
                    return b-a;
                }

            });
            StringBuilder sb = new StringBuilder();
            int itemCount = 0;
            for (Object item : unsortedList) {
                if (itemCount++ > 0 && itemCount <= MAX_LIST) sb.append(", ");
                if (itemCount > MAX_LIST) break;
                if (scoreAndUid.get(item) != null) {
                sb.append(item.toString()+"="
                        +scoreAndUid.get(item).toString());
                }
            }
            return sb.toString();
        }
        return null;
    }

    public void updateDate(String sessionkey) {
        sessionKeyMap.put(sessionkey,new Date(new Date().getTime()+SESSION_TIMEOUT));
    }
}