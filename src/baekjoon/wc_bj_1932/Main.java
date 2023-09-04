package baekjoon.wc_bj_1932;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] tri = new int[N][N];

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < i + 1; j++){
                tri[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][N+1];

        dp[0][1] = tri[0][0];

        for(int i = 1; i < N; i++){
            for(int j = 1; j <= N; j++){
                dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + tri[i][j - 1];
            }
        }

        int max = 0;
        for(int i = 1; i <= N; i++){
            max = Math.max(max, dp[N-1][i]);
        }

        System.out.println(max);
    }
}
