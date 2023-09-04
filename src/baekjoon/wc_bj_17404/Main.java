package baekjoon.wc_bj_17404;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] color = new int[N][3];

        // 색깔을 받아보자
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][3];

        int min = 1_000_001;
        // 첫 집 색깔
        for(int k = 0; k < 3; k++){
            dp[0][k] = color[0][k];
            dp[0][(k+1)%3] = 1001;
            dp[0][(k+2)%3] = 1001;

            // dp 채우기
            for(int i = 1; i < N; i++){
                for(int j = 0; j < 3; j++){
                    dp[i][j] = color[i][j] + Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]);
                }
            }

            min = Math.min(min, dp[N-1][(k+1)%3]);
            min = Math.min(min, dp[N-1][(k+2)%3]);
        }

        System.out.println(min);
    }
}
