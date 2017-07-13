package com.example.administrator.ptrultrawithloadmore.util;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class CollectionUtils {
    public static <T> boolean isValid(Collection<T> c) {
        return !(c == null || c.isEmpty());
    }
    /**
     * @param <T> 
     * @param arrs 
     * @param index
     * @return 
     */
    public static <T> T[] removeArrayItem(T[] arrs, int index) {
        int len = arrs.length;
        if(index < 0 || index >= len) {
            throw new IllegalArgumentException("index");
        }
        List<T> list = new LinkedList<T>();
        for(int i = 0; i < len; i++) {
            if(i != index) {
                list.add(arrs[i]);
            }
        }
        
        arrs = list.toArray(arrs);
        return java.util.Arrays.copyOf(arrs, arrs.length - 1);
    }
}
