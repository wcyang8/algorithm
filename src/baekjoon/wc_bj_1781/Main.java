package baekjoon.wc_bj_1781;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 시간의 흐름에 따라
 * 만약 처리 못하는게 있다면?
 * 과거의 개수와 비교한다
 * -> 이러면 시간복잡도 20만 x 20만이 될거 같은데?
 */

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->((o1[0]-o2[0])!=0?(o1[0]-o2[0]):(o2[1]-o1[1])));
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            pq.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        PriorityQueue<Integer> cup = new PriorityQueue<>();

        // 시간이 감에 따라
        int now = 1;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            System.out.println("now : "+now);
            // 데드라인 전
            if(now <= cur[0]){
                System.out.println("add : "+cur[1]);
                cup.add(cur[1]);
                ++now;
            }else{
                if(cup.peek() < cur[1]){
                    System.out.println("out : "+cup.peek());
                    cup.poll();
                    System.out.println("add : "+cur[1]);
                    cup.add(cur[1]);
                }
            }
        }

        int sum = 0;
        while(!cup.isEmpty()){
            sum += cup.poll();
        }

        System.out.println(sum);
    }
}
