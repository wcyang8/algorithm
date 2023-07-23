package baekjoon.wc_bj_16931;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] block = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                block[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 2 * M * N;

        for(int i = 0; i < N; i++){
            int cur = 0;
            for(int j = 0; j < M; j++){
                ans += Math.abs(cur - block[i][j]);
                cur = block[i][j];
            }
            ans += cur;
        }

        for(int j = 0; j < M; j++){
            int cur = 0;
            for(int i = 0; i < N; i++){
                ans += Math.abs(cur - block[i][j]);
                cur = block[i][j];
            }
            ans += cur;
        }

        System.out.println(ans);
    }
}
