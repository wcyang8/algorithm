package baekjoon.wc_bj_11048;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[][] dir = {{1,0},{0,1},{1,1}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] maze = new int[N][M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                int before = 0;

                for(int[] d : dir){
                    int ni = i - d[0];
                    int nj = j - d[1];

                    if(ni >= 0 && nj >= 0){
                        before = Math.max(before, dp[ni][nj]);
                    }
                }
                dp[i][j] = before + maze[i][j];
            }
        }

        System.out.println(dp[N-1][M-1]);
    }
}
