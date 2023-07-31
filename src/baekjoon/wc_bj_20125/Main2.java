package baekjoon.wc_bj_20125;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main2 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];

        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++){
                board[i][j] = s.charAt(j)=='*'?1:0;
            }
        }

        int[][] visited = new int[N][N];

        int hi = -1;
        int hj = -1;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(board[i][j] == 1){
                    if(j+1 >= N || (j+1 < N && board[i][j+1] == 1)){
                        visited[i][j]++;
                    }
                }
            }
        }


    }
}
