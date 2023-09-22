package baekjoon.wc_bj_12100;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int[][] board = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = move(board, 0);

        System.out.println(max);
    }

    private static int move(int[][] board, int time) {
        int max = 0;
//        System.out.println("현재 : " + time);
//        printTest(board);
        if(time >= 5){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    max = Math.max(max,board[i][j]);
                }
            }
            return max;
        }

        int[][] curBoard = new int[N][N];

        Stack<int[]> s = new Stack<>();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(board[i][j] == 0) continue;
                if(!s.isEmpty() && s.peek()[0] == board[i][j] && s.peek()[1] == 0){
                    s.pop();
                    s.add(new int[]{board[i][j] * 2, 1});
                }else if(board[i][j] != 0) s.add(new int[]{board[i][j], 0});
            }
            int pos = 0;
            int size = s.size();
            while(!s.isEmpty()){
                curBoard[i][size - pos++ - 1] = s.pop()[0];
            }
        }
//        System.out.println("왼");
//        printTest(curBoard);
        max = Math.max(max, move(curBoard, time + 1));


        curBoard = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(board[j][i] == 0) continue;
                if(!s.isEmpty() && s.peek()[0] == board[j][i] && s.peek()[1] == 0){
                    s.pop();
                    s.add(new int[]{board[j][i] * 2, 1});
                }else if(board[j][i] != 0) s.add(new int[]{board[j][i], 0});
            }
            int pos = 0;
            int size = s.size();
            while(!s.isEmpty()){
                curBoard[size - pos++ - 1][i] = s.pop()[0];
            }
        }
//        System.out.println("위");
//        printTest(curBoard);
        max = Math.max(max, move(curBoard, time + 1));


        curBoard = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(board[i][N - j - 1] == 0) continue;
                if(!s.isEmpty() && s.peek()[0] == board[i][N - j - 1] && s.peek()[1] == 0){
                    s.pop();
                    s.add(new int[] {board[i][N - j - 1] * 2, 1});
                }else if(board[i][N - j - 1] != 0) s.add(new int[]{board[i][N - j - 1], 0});
            }
            int pos = 0;
            int size = s.size();
            while(!s.isEmpty()){
                curBoard[i][N + pos++ - size] = s.pop()[0];
            }
        }
//        System.out.println("오");
//        printTest(curBoard);
        max = Math.max(max, move(curBoard, time + 1));


        curBoard = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(board[N - j - 1][i] == 0) continue;
                if(!s.isEmpty() && s.peek()[0] == board[N - j - 1][i] && s.peek()[1] == 0){
                    s.pop();
                    s.add(new int[] {board[N - j - 1][i] * 2, 1});
                }else if(board[N - j - 1][i] != 0) s.add(new int[] {board[N - j - 1][i], 0});
            }
            int pos = 0;
            int size = s.size();
            while(!s.isEmpty()){
                curBoard[N + pos++ - size][i] = s.pop()[0];
            }
        }
//        System.out.println("아");
//        printTest(curBoard);
        max = Math.max(max, move(curBoard, time + 1));

        return max;
    }

    public static void printTest(int[][] board){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
