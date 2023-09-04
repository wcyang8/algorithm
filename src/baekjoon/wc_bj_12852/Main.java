package baekjoon.wc_bj_12852;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N+1][2];

        dp[1][0] = 0;   // 연산 횟수
        dp[1][1] = -1;   // 마지막 수
        for(int i = 2; i <= N; i++){
            int min = Integer.MAX_VALUE;
            int last = -1;
            if(i % 3 == 0){
                if(min > dp[i/3][0]){
                    min = dp[i/3][0];
                    last = i/3;
                }
            }
            if(i % 2 == 0){
                if(min > dp[i/2][0]){
                    min = dp[i/2][0];
                    last = i/2;
                }
            }
            if(min > dp[i-1][0]){
                min = dp[i-1][0];
                last = i-1;
            }
            dp[i][0] = min + 1;
            dp[i][1] = last;
        }

        System.out.println(dp[N][0]);
        while(N > 0){
            System.out.print(N + " ");
            N = dp[N][1];
        }
    }
}
