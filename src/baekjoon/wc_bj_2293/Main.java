package baekjoon.wc_bj_2293;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coin = new int[N];
        int[] dp = new int[K+1];

        for(int i = 0; i < N; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }
        dp[0] = 1;

        // K원이 되는 경우의 수
        for(int i = 0; i < N; i++){
            for(int k = 0; k <= K; k++){
                if(k - coin[i] >= 0){
                    dp[k] += dp[k-coin[i]];
                }
            }
        }

        System.out.println(dp[K]);
    }
}
