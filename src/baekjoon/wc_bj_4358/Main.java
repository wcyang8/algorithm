package baekjoon.wc_bj_4358;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Map<String, Integer> map = new TreeMap<>();
        int[] tree = new int[10000];

        int t = 0;
        int num = 0;
        String key;
        while((key = br.readLine()) != null) {
            if(key.isEmpty() || key.trim().isEmpty()) break;
            if(!map.containsKey(key)){
                map.put(key, t);
                tree[t]++;
                t++;
            }else{
                tree[map.get(key)]++;
            }
            num++;
        }

        for(Map.Entry<String, Integer> entry : map.entrySet()){
            sb.append(entry.getKey() + " " + String.format("%.4f",(double)tree[entry.getValue()] * 100 / num)).append('\n');
        }

        System.out.print(sb);
    }
}
