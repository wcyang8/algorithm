package baekjoon.wc_bj_1715;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++){
            pq.add(Integer.parseInt(br.readLine()));
        }


        int ans = 0;
        while(pq.size() > 1){
            int a = pq.poll();
            int b = pq.poll();

            pq.add(a + b);
            ans += a + b;
        }
        System.out.println(ans);

//        int[] set = new int[N];
//
//        Arrays.sort(set);
//
//        for(int i = 0; i < N; i++){
//            set[i] = Integer.parseInt(br.readLine());
//        }
//
//        long sum = set[0] * --N;
//        for(int i = 1; i < set.length; i++){
//            sum += set[i] * N--;
//        }
//
//        System.out.println(sum);
    }
}
