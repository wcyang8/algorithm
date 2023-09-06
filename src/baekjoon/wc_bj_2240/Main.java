package baekjoon.wc_bj_2240;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        boolean[] jadoo = new boolean[T];

        for(int i = 0; i < T; i++){
            jadoo[i] = Integer.parseInt(br.readLine()) == 1;
        }

        int[][] dp = new int[T][W + 1];

        dp[0][0] = jadoo[0]?1:0;
        dp[0][1] = jadoo[0]?0:1;
        for(int t = 1; t < T; t++){
            dp[t][0] = dp[t-1][0] + (jadoo[t]?1:0);
            for(int w = 1; w <= W; w++){
                // t번째 자두가 1, w가 짝수 || t번째 자두가 2, w가 홀수
                boolean check = (jadoo[t] ^ (w%2==1));
                dp[t][w] = Math.max(dp[t-1][w] + (check?1:0),dp[t-1][w-1] + (!check?1:0));
            }
        }

        int max = 0;
        for(int w = 0; w <= W; w++){
            max = Math.max(max,dp[T-1][w]);
        }
        System.out.println(max);
    }
}
