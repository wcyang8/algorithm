package baekjoon.wc_bj_1799;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] board, dir = {{1,1},{1,-1},{-1,1},{-1,-1}};
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        int left = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 1) ++left;
            }
        }

        System.out.println(backT(0, 0, left));
    }

    private static int backT(int pos, int num, int left) {
        if(pos >= N*N){
            return num;
        }

        int i = pos / N;
        int j = pos % N;

        if(board[i][j] == 2) return backT(pos + 1, num, left - 1);

        int max = 0;
        int checked = check(i, j);
        if(checked != -1){
            board[i][j] = 2;
            max = backT(pos + 1, num + 1, left - checked);
            board[i][j] = 1;
        }
        max = Math.max(max, backT(pos + 1, num, left - 1));

        return max;
    }

    private static int check(int i, int j) {
        int res = 1;
        for(int[] d : dir){
            int ni = i + d[0];
            int nj = j + d[1];
            while(ni >= 0 && ni < N && nj >= 0 && nj < N){
                if(board[ni][nj] == 2) return -1;
                else if(board[ni][nj] == 1) ++res;
                ni += d[0];
                nj += d[1];
            }
        }
        return res;
    }
}
