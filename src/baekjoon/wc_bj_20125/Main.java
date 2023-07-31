package baekjoon.wc_bj_20125;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

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
                    if(i + 1 < N && board[i+1][j] == 1
                    && j + 1 < N && board[i][j+1] == 1
                    && i - 1 >= 0 && board[i-1][j] == 1
                    && j - 1 >= 0 && board[i][j-1] == 1) {
                        hx = i;
                        hy = j;
                        break;
                    }
                }
            }
        }
        // 아무튼 찾아짐
//        System.out.println(hx + " " + hy);

        int k = -1;
        for(int t = 0; t < 4; t++){
            if(len(board, hx+d[t][0], hy+d[t][1], t) == 1) break;
            int tx = nx + d[(t+1)%4][0];
            int ty = ny + d[(t+1)%4][1];
//            System.out.println(tx + " " + ty);
            if(tx >= 0 && tx < N && ty >= 0 && ty < N && board[tx][ty] == 1){
                k = (t+2) % 4;
                break;
            }
        }
//        System.out.println(k);

        System.out.println((hx + 1) + " " + (hy + 1));
        System.out.print(len(board, hx + d[(k+1)%4][0], hy + d[(k+1)%4][1], (k+1)%4) + " "
        + len(board, hx+d[(k+3)%4][0], hy+d[(k+3)%4][1], (k+3)%4) + " "
        + len(board, hx+d[(k+2)%4][0], hy+d[(k+2)%4][1], (k+2)%4) + " ");
        int tx = nx;
        int ty = ny;
        System.out.println(len(board, tx+d[(k+1)%4][0], ty+d[(k+1)%4][1], (k+2)%4) + " "
        + len(board, tx+d[(k+3)%4][0], ty+d[(k+3)%4][1], (k+2)%4));

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
