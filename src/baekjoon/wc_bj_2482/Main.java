package baekjoon.wc_bj_2482;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 점화식
 * dp[N][K] = dp[N-3][K-1] * N
 */

public class Main {
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
//        for(int i = 4; i <= N; i++){
//            for(int j = 1; j <= i / 2; j++){
//                if(j == 1) {
//                    dp[i][j] = i;
//                }else if(i % 2 == 0 && j == i / 2){
//                    dp[i][j] = 2;
//                }else if(j == 2){
//                    dp[i][j] = (i * (i-3)) / 2;
//                }else{
//                    long temp = dp[i-3][j-1];
//                    int k = 1;
//                    while(i - 3 - 4 * k >= 4 && j - 1 - 2 * k > 0){
//                        temp += dp[i-3-4*k][j-1-2*k];
//                        ++k;
//                    }
//                    dp[i][j] = ((temp * i) / 2) % R;
//                }
////                System.out.println("(i,j) = "+i+" "+j);
////                System.out.println(dp[i][j]);
//            }
//        }

        System.out.println(dp[N][K]);
    }
}
