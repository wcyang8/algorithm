package baekjoon.wc_bj_14235;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2)->(o2-o1));
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());

            if(M == 0){
                if(pq.isEmpty()) sb.append(-1).append("\n");
                else sb.append(pq.poll()).append("\n");
            }else{
                for(int j = 0; j < M; j++){
                    pq.add(Integer.parseInt(st.nextToken()));
                }
            }
        }

        System.out.print(sb);
    }
}
