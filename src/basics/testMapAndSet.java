package basics;

import java.io.*;
import java.util.*;

public class testMapAndSet {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> map = new HashMap();

        map.put("rrr", 1);
        System.out.println(map.put("abc", 2));

        System.out.println(map.get("abab"));
        System.out.println(map.get("abc"));

        Set<Integer> set = new HashSet<>();

        set.add(1);
        set.add(2);

        for(int i : set){
            System.out.println(i);
        }

        TreeMap<String, Integer> tm = new TreeMap<>();

        tm.put("dbab", 3);
        tm.put("cccc", 2);
        tm.put("cccc", 3);

        System.out.println(tm);
        for(Map.Entry<String, Integer> s :tm.entrySet()){
            System.out.println(s);
        }

        TreeSet<Integer> ts = new TreeSet<>();

        ts.add(1);
        ts.add(1);
        ts.add(5);
        ts.add(2);

        System.out.println(ts);

        for(Integer i : ts){
            System.out.println(i);
        }
    }
}
