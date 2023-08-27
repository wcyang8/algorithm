package baekjoon.wc_bj_2696;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++){
            int M = Integer.parseInt(br.readLine());

            sb.append((M + 1) / 2).append("\n");

            StringTokenizer st = new StringTokenizer(br.readLine());

            PriorityQueue<Integer> left = new PriorityQueue<>((o1,o2)->(o2-o1));
            PriorityQueue<Integer> right = new PriorityQueue<>();
            left.add(Integer.MIN_VALUE);
            right.add(Integer.MAX_VALUE);

            int mid = Integer.parseInt(st.nextToken());
            sb.append(mid).append(' ');
            M--;

            while(M > 0){
                int[] list = new int[5];
                list[0] = mid;
                list[1] = Integer.parseInt(st.nextToken());
                list[2] = Integer.parseInt(st.nextToken());
                list[3] = left.peek();
                list[4] = right.peek();

                Arrays.sort(list);

                left.add(list[0]);
                left.add(list[1]);
                mid = list[2];
                right.add(list[3]);
                right.add(list[4]);

                sb.append(mid).append(' ');
                M -= 2;
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
