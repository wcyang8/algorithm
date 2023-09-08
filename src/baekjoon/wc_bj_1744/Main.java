package baekjoon.wc_bj_1744;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pos = new PriorityQueue<>((o1,o2)->(o2-o1));
        PriorityQueue<Integer> neg = new PriorityQueue<>();
        boolean zero = false;

        long ans = 0;

        for(int i = 0; i < N; i++){
            int cur = Integer.parseInt(br.readLine());

            if(cur > 1) pos.add(cur);
            else if(cur == 1) ans++;
            else if(cur <= 0) neg.add(cur);
        }

        while(!pos.isEmpty()){
            if(pos.size() == 1) ans += pos.poll();
            else ans += pos.poll() * pos.poll();
        }

        while(!neg.isEmpty()){
            if(neg.peek() == 0) break;
            if(neg.size() == 1) ans += neg.poll();
            else ans += neg.poll() * neg.poll();
        }

        System.out.println(ans);
    }
}
