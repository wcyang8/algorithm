package baekjoon.wc_bj_2133;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * f(n) = f(n-2) * 3 + f(n-4) * 2;
 *
 */

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        if(N == 1){
            System.out.println(0);
            return;
        }

        int[] dp = new int[N+1];

        dp[0] = 1;
        dp[2] = 3;

        for(int i = 4; i <= N; i += 2){
            dp[i] = dp[i-2] * 3;
            int k = 2;
            while(i >= 2 * k){
                dp[i] += dp[i - 2 * k] * 2;
                k++;
            }
        }

        System.out.println(dp[N]);
    }
}
