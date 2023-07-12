package baekjoon.wc_bj_1937;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        int[][] forest = new int[N][N];

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2)->(o1[2] - o2[2]));
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                forest[i][j] = Integer.parseInt(st.nextToken());
                int[] temp = {i,j,forest[i][j]};
                pq.add(temp);
            }
        }

        int[][][] check = new int[N][N][2];     // 위치마다 0: 이전 위치의 대나무 수, 1: 이동거리 저장

        int max = 0;
//        for(int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//        max = Math.max(max,dfs(forest, check, i, j, 1));
//            }
//        }
//        System.out.println(pq.peek()[2]);
        while(!pq.isEmpty()){
            int[] temp = pq.poll();
            int i = temp[0];
            int j = temp[1];
            max = Math.max(max,dfs(forest, check, i, j, 1));
        }
        System.out.println(max + 1);
    }

    private static int dfs(int[][] forest, int[][][] check, int x, int y, int dist){
        int max = 0;
        for(int[] d : dir){
            int nx = x + d[0];
            int ny = y + d[1];

            if(nx >= 0 && nx < N && ny >= 0 && ny < N
                    && forest[nx][ny] > forest[x][y] && check[nx][ny][1] <= dist){
                check[nx][ny][0] = forest[x][y];
                check[nx][ny][1] = dist + 1;
                max = Math.max(max, 1 + dfs(forest,check,nx,ny,dist+1));
            }
        }
        return max;
    }
}
