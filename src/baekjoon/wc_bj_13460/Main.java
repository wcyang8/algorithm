package baekjoon.wc_bj_13460;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static char[][] board;
    static int[][][][] visited;
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static int N, M;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];

        int ri = 0, rj = 0, bi = 0, bj = 0;
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            board[i] = s.toCharArray();
            int tempR = s.indexOf('R');
            int tempB = s.indexOf('B');
            if(tempR != -1){
                ri = i;
                rj = tempR;
                board[ri][rj] = '.';
            }
            if(tempB != -1){
                bi = i;
                bj = tempB;
                board[bi][bj] = '.';
            }
        }

        visited = new int[N][M][N][M];

        Queue<int[]> q = new ArrayDeque<>();

        visited[ri][rj][bi][bj] = 1;
        q.add(new int[] {ri, rj, bi, bj, 1});

        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[4] > 10){
                System.out.println(-1);
                return;
            }

//            print(cur[0],cur[1],cur[2],cur[3]);

            for(int[] d : dir) {
                int nr = 0, nb = 0, nri = 0, nrj = 0, nbi = 0, nbj = 0;
                // 가는 길에 B가 있는지 체크
                if (checkB(cur[0], cur[1], cur[2], cur[3], d) == -1){
//                    System.out.println("B가 있다");
                    // 있으면 B 먼저
                    // 가는 길에 O가 있는지 체크, 도착점 return
                    nb = checkO(cur[2], cur[3], cur[0], cur[1], d);
                    nbi = nb / M;
                    nbj = nb % M;
                    nr = checkO(cur[0], cur[1], nbi, nbj, d);
                    nri = nr / M;
                    nrj = nr % M;
                }else{
                    nr = checkO(cur[0], cur[1], cur[2], cur[3], d);
                    nri = nr / M;
                    nrj = nr % M;
                    nb = checkO(cur[2], cur[3], nri, nrj, d);
                    nbi = nb / M;
                    nbj = nb % M;
                }
//                System.out.println("\t" + nri + " " + nrj + " " + nbi + " " + nbj);
                if(nb == -1) continue;
                if(nr == -1) {
                    System.out.println(cur[4]);
                    return;
                }
                // visted 체크
                if(visited[nri][nrj][nbi][nbj] == 0){
                    // 이동
                    visited[nri][nrj][nbi][nbj] = 1;
                    q.add(new int[]{nri,nrj,nbi,nbj,cur[4]+1});
                }
            }
        }
        System.out.println(-1);
    }

    static int checkB(int ai, int aj, int bi, int bj, int[] d){
        // ai, aj를 d 방향으로 계속 움직인다. c를 만나면 -1을 return 한다.
        while(board[ai][aj] == '.' && !(ai == bi && aj == bj)){
            ai += d[0];
            aj += d[1];
        }
        if(ai == bi && aj == bj) return -1;
        return (ai - d[0]) * M + (aj - d[1]);
    }

    static int checkO(int ai, int aj, int bi, int bj, int[] d){
        while(board[ai][aj] == '.' && !(ai == bi && aj == bj)){
            ai += d[0];
            aj += d[1];
        }
        if(board[ai][aj] == 'O') return -1;
        return (ai - d[0]) * M + (aj - d[1]);
    }

    static void print(int ri, int rj, int bi, int bj){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(i == ri && j == rj) {
                    System.out.print("R ");
                    continue;
                }
                if(i == bi && j == bj){
                    System.out.print("B ");
                    continue;
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}