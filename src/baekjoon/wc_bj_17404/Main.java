package baekjoon.wc_bj_17404;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] house = new int[N][3];      // R G B

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        int[][] dp = new int[N][3];

        for(int i = 1; i < N; i++){
            for(int j = 0; j < 3; j++){
                house[i][j] += Math.min(house[i-1][(j+1)%3], house[i-1][(j+2)%3]);
            }
        }

        int min = Math.min(house[N-1][0],Math.min(house[N-1][1],house[N-1][2]));
        System.out.println(min);
    }
}
