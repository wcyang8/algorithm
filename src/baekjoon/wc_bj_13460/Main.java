package baekjoon.wc_bj_13460;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static String[] board;
    static int[][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        board = new String[N];

        int ri = 0, rj = 0, bi = 0, bj = 0;
        for(int i = 0; i < N; i++){
            board[i] = br.readLine();
            int tempR = board[i].indexOf('R');
            int tempB = board[i].indexOf('B');
            if(tempR != -1){
                ri = i;
                rj = tempR;
            }
            if(tempB != -1){
                bi = i;
                bj = tempB;
            }
        }

        visited = new int[N][M];

        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[] {ri, rj, bi, bj, 0});

        while(!q.isEmpty()){

        }
        // 가는 길에 O가 있는지 체크
        // 가는 길에 B가 있는지 체크
        // 있다면 B 보낸다 (isBlue = 1)
        // 도착 점이 visited 인지 체크
        // R 보낸다
        // 없다면 B 보낸다 (isBlue = 0)


    }
}
