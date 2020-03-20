package com.emsoft.riskwaring.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListToMapUtil {
    public static <T> List<Map<String, T>> toMap(List<T[]> list, String... parms) {

        List<Map<String, T>> resultList = new ArrayList<>();


        for (T[] listResult : list) {
            Map<String, T> map = new HashMap<>(listResult.length);
            int i = 0;
            for (T data : listResult) {
                map.put(parms[i], data);
                i += 1;
            }
            resultList.add(map);
        }
        return resultList;
    }
}
