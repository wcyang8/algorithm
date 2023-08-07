package baekjoon.wc_bj_1700;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 멀티탭 구멍 개수
 * 전기용품 사용 횟수
 *
 */

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

//        int[] elec = new int[K];

        List<Integer>[] history = new LinkedList[K+1];

        for(int i = 1; i <= K; i++){
            history[i] = new LinkedList<>();
        }

        st = new StringTokenizer(br.readLine());

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 1; i <= K; i++){
            int num = Integer.parseInt(st.nextToken());
            history[num].add(i);
            q.add(num);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2)
                -> ((history[o2].isEmpty()?101:history[o2].get(0))
                - (history[o1].isEmpty()?101:history[o1].get(0))));

        int ans = 0;
        // N개 검색해서
        for(int i = 0; i < K; i++) {
            // history가 변경되었을 때 pq가 갱신되지 않는 문제가 생김.
            // 이를 해결하기 위한 코드.
            Queue<Integer> temp = new ArrayDeque<>();
            while(!pq.isEmpty()){
                temp.add(pq.poll());
            }
            while(!temp.isEmpty()){
                int x = temp.poll();
//                System.out.print(x);
                pq.add(x);
            }
//            System.out.println();

            int cur = q.poll();
            history[cur].remove(0);


            // 현재 실행하는 전기용품이 있으면?
            // 다음 전기용품
            if(pq.contains(cur)) continue;

            // 멀티탭이 꽉차있으면
            if(pq.size() >= N){
                // 멀티탭에서 1개를 뽑는다
//                System.out.println("pop : "+pq.poll());
                pq.poll();
                // 뽑은 횟수 +1
                ans++;
            }
            // 새로운 기기를 꽂아준다.
            pq.add(cur);
        }
        System.out.println(ans);
    }
}
