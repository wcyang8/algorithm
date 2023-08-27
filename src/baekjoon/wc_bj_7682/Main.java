package baekjoon.wc_bj_7682;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * X가 이긴 경우
 *  X = O + 1
 *  X가 3개 && !O는 3개
 * O가 이긴 경우
 *  X = O
 *  O가 3개 && !X는 3개
 */
public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String s;
        while(!(s = br.readLine()).equals("end")){
            char[][] board = new char[3][3];

            int xNum = 0;
            int oNum = 0;
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    board[i][j] = s.charAt(i*3 + j);
                    if(board[i][j] == 'X') xNum++;
                    else if(board[i][j] == 'O') oNum++;
                }
            }

            int xWin = check(board, 'X');
            int oWin = check(board, 'O');

            // 게임판이 가득 찬 경우
            if(xNum == 5 && oNum == 4 && xWin == 0 && oWin == 0) {
                sb.append("valid").append("\n");
                continue;
            }
            // X가 이긴 경우
            if(xNum == oNum + 1 && xWin >= 1 && oWin == 0) {
                sb.append("valid").append("\n");
                continue;
            }
            // O가 이긴 경우
            if(xNum == oNum && xWin == 0 && oWin >= 1) {
                sb.append("valid").append("\n");
                continue;
            }
            // 그 외의 경우
            sb.append("invalid").append("\n");
        }

        System.out.print(sb);
    }

    private static int check(char[][] board, char x) {
        int res = 0;
        // 가로 탐색
        for(int i = 0; i < 3; i++){
            boolean ok = true;
            for(int j = 0; j < 3; j++){
                if(board[i][j] != x){
                    ok = false;
                    break;
                }
            }
            if(ok) res++;
        }
        // 세로 탐색
        for(int j = 0; j < 3; j++){
            boolean ok = true;
            for(int i = 0; i < 3; i++){
                if(board[i][j] != x){
                    ok = false;
                    break;
                }
            }
            if(ok) res++;
        }
        // 대각선 탐색
        boolean ok = true;
        for(int k = 0; k < 3; k++){
            if(board[k][k] != x){
                ok = false;
                break;
            }
        }
        if(ok) res++;
        ok = true;
        for(int k = 0; k < 3; k++){
            if(board[k][2-k] != x){
                ok = false;
                break;
            }
        }
        if(ok) res++;
        return res;
    }
}
