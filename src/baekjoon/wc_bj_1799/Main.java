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

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(backT(0, 0));
    }

    private static int backT(int pos, int num) {
        if(pos >= N*N){
            return num;
        }

        int i = pos / N;
        int j = pos % N;

        if(board[i][j] == 0) return backT( pos + 1, num);

        int max = 0;
        if(check(i, j)){
            board[i][j] = 2;
            max = backT(pos + 1, num + 1);
            board[i][j] = 1;
        }
        max = Math.max(max, backT(pos + 1, num));

        return max;
    }

    private static boolean check(int i, int j) {
        for(int[] d : dir){
            int ni = i + d[0];
            int nj = j + d[1];
            while(ni >= 0 && ni < N && nj >= 0 && nj < N){
                if(board[ni][nj] == 2) return false;
                ni += d[0];
                nj += d[1];
            }
        }
        return true;
    }
}
