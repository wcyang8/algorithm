package baekjoon.wc_bj_20125;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main3 {

    static int[][] d = {{1,0},{0,1},{-1,0},{0,-1}};
    static int nx, ny;
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

        int hx = -1;
        int hy = -1;

        // 심장 위치 찾기
        for(int i = 0; i < N; i++){
            if(hx != -1 && hy != -1) break;
            for(int j = 0; j < N; j++){
                if(board[i][j] == 1){
                    hx = i + 1;
                    hy = j;
                    break;
                }
            }
        }

        System.out.println((hx + 1) + " " + (hy + 1));
        System.out.print(len(board, hx, hy-1, 3) + " "
                + len(board, hx, hy+1, 1) + " "
                + len(board, hx+1, hy, 0) + " ");
        int tx = nx;
        int ty = ny;
        System.out.println(len(board, tx, ty-1, 0) + " "
                + len(board, tx, ty+1, 0));

    }

    private static int len(int[][] board, int sx, int sy, int k) {
        int len = 0;

        int N = board.length;
        nx = sx;
        ny = sy;
        while(nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == 1){
            len++;
            nx += d[k][0];
            ny += d[k][1];
        }

        return len;
    }

}
