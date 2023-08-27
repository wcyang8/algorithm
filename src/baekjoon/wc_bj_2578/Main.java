package baekjoon.wc_bj_2578;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[5][5];
    static boolean[][] check = new boolean[5][5];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // 입력
        for(int i = 0; i < 5; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 입력 끝

        int bingo = 0;
        // 반복문
        for(int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                int cur = Integer.parseInt(st.nextToken());
                // 하나 체크
                int[] pos = visit(cur);
                // 빙고 체크
                bingo += isBingo(pos[0], pos[1]);

                if(bingo >= 3){
                    System.out.println(i*5 + j + 1);
                    return;
                }
            }
        }
        // 반복문 끝
    }

    private static int isBingo(int i, int j) {
        int res = 0;

        boolean row = true;
        for(int x = 0; x < 5; x++){
            if(!check[x][j]) {
                row = false;
            }
        }
        if(row) res++;

        boolean col = true;
        for(int y = 0; y < 5; y++){
            if(!check[i][y]) col = false;
        }
        if(col) res++;

        // 왼쪽 위 - 오른쪽 아래 대각선
        boolean dia1 = true;
        if(i == j){
            for(int k = 0; k < 5; k++){
                if(!check[k][k]) dia1 = false;
            }
        }else{
            dia1 = false;
        }
        if(dia1) res++;

        boolean dia2 = true;
        if(i+j == 4){
            for(int k = 0; k < 5; k++){
                if(!check[k][4-k]) dia2 = false;
            }
        }else{
            dia2 = false;
        }
        if(dia2) res++;

        return res;
    }

    private static int[] visit(int cur) {
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(board[i][j] == cur) {
                    check[i][j] = true;
                    return new int[]{i,j};
                }
            }
        }
        // error
        return new int[]{-1,-1};
    }
}
