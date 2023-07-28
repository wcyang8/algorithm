package baekjoon.wc_bj_2482;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * dp[N][K] = dp[N-3][K-1] + dp[N-1][K];
 */

public class Main2 {
    static int R = 1_000_000_003;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        if(K > N/2){
            System.out.println(0);
            return;
        }

        long[][] dp = new long[N+1][N/2+1];

        // init
        for(int i = 1; i <= N; i++){
            dp[i][1] = i;
        }

        // dp
        for(int i = 4; i <= N; i++){
            for(int j = 2; j <= i/2; j++){
                dp[i][j] = (dp[i-2][j-1] + dp[i-1][j]) % R;
            }
        }

        System.out.println(dp[N][K]);
    }
}
