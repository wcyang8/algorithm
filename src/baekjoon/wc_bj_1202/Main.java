package baekjoon.wc_bj_1202;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] jewel = new int[N][2];
        int[] bag = new int[K];
//        List<Integer> bag = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            jewel[i][0] = Integer.parseInt(st.nextToken());
            jewel[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) {
//            bag.add(Integer.parseInt(br.readLine()));
            bag[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(jewel, (o1, o2) -> ((o1[0] - o2[0]) == 0 ? (o2[1] - o1[1]) : (o1[0] - o2[0])));

//        for(int i = 0; i < N; i++){
//            System.out.println(jewel[i][0] + " " + jewel[i][1]);
//        }
//        Collections.sort(bag);
        Arrays.sort(bag);

        long sum = 0;
//        for(int i = 0; i < K; i++){
//            bag[i]
//        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> (o2 - o1));
        int pos = 0;
        for (int i = 0; i < K; i++) {
            int temp = bag[i];
            int left = pos;
            int right = N - 1;
            int idx = -1;
            while (left <= right) {
                int mid = (left + right) / 2;

                if (jewel[mid][0] > temp) {
                    right = mid - 1;
                } else if (jewel[mid][0] <= temp) {
                    idx = mid;
                    left = mid + 1;
                }
            }
//            int idx = left - 1;
//            System.out.println("pos : " + pos + " idx : " + idx);

            for (int j = pos; j <= idx; j++) {
                pq.add(jewel[j][1]);
            }

            if (!pq.isEmpty()) {
                sum += pq.poll();
            }

            if(idx != -1) {
                pos = idx + 1;
            }
        }

        System.out.println(sum);
    }
}

