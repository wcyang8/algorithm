package baekjoon.wc_bj_9375;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++){
            int N = Integer.parseInt(br.readLine());

            HashMap<String, HashSet<String>> map = new HashMap<>();

            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String value = st.nextToken();
                String key = st.nextToken();
                if(map.get(key) == null){
                    HashSet<String> s = new HashSet<>();
                    s.add(value);
                    map.put(key, s);
                }
            }


        }
    }
}
